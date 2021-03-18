package group.springbootdemo;

import group.springbootdemo.model.Customer;
import group.springbootdemo.model.Seller;
import group.springbootdemo.repository.CustomerRepository;
import group.springbootdemo.repository.SellerRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class someTest {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void addCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFname("Everest");
        customer.setStatus(1);

        Seller steve = sellerRepository.findByFname("Steve");
        steve.setCustomers(Arrays.asList(customer));

        sellerRepository.save(steve);
    }

    @Test
    public void deleteCustomer() throws Exception {
        List<Customer> customers = customerRepository.findByFname("Everest");
        assertThat(customers).hasSize(1);

        customerRepository.delete(customers.get(0));

        List<Seller> sellers = sellerRepository.findAll();
        assertThat(sellers).hasSize(3);

    }
}
