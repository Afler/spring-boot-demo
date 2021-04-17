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
public class UserService implements UserDetailsService { // UserDetailService used by SpringSecurityContext

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

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user, Seller sellerParam) {

        user.setActive(true);
        userRepository.save(user);
        if (user.getRoles().contains(Role.CUSTOMER)) {
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

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
