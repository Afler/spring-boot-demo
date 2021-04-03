package group.springbootdemo.service;

import group.springbootdemo.model.Customer;
import group.springbootdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(int id) {
        customerRepository.deleteById(id);
    }

    public Customer findCustomerByName(String name) { return customerRepository.findCustomerByFname(name);}
}
