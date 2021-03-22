package group.springbootdemo.service;

import group.springbootdemo.model.Customer;
import group.springbootdemo.model.Role;
import group.springbootdemo.model.Seller;
import group.springbootdemo.model.User;
import group.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService { // UserDetService used by SpringSecurityContext

    private final UserRepository userRepository;

    private final CustomerService customerService;

    private final SellerService sellerService;

    @Autowired
    public UserService(UserRepository userRepository, CustomerService customerService, SellerService sellerService) {
        this.userRepository = userRepository;
        this.customerService = customerService;
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user, Seller sellerParam) {

        user.setActive(true);
        userRepository.save(user);
        if (user.getRoles().contains(Role.USER)) {
            Customer customer = new Customer();
            customer.setSeller(sellerService.findSellerById(sellerParam.getId()));
            customer.setUser(userRepository.findByUsername(user.getUsername()));
            customer.setFname(user.getUsername());
            customerService.saveCustomer(customer);
        } else {
            Seller seller = new Seller();
            seller.setUser(userRepository.findByUsername(user.getUsername()));
            seller.setFname(user.getUsername());
            sellerService.saveSeller(seller);
        }

        return user;
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
