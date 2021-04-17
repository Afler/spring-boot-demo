package group.springbootdemo;

import group.springbootdemo.model.*;
import group.springbootdemo.repository.CustomerRepository;
import group.springbootdemo.repository.SellerRepository;
import group.springbootdemo.service.CustomerService;
import group.springbootdemo.service.DetailService;
import group.springbootdemo.service.OrderService;
import group.springbootdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class someTest {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DetailService detailService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Test
    public void test() throws Exception {
        Detail detail = new Detail();
        detail.setName("двигатель");
        detail.setPrice(200.2);
        detail.setQuantity(5);

        detailService.save(detail);

        Set<Role> roles_u = new HashSet<Role>(Arrays.asList(Role.CUSTOMER));
        Set<Role> roles_s = new HashSet<Role>(Arrays.asList(Role.SELLER));

        Seller seller = new Seller();

        User user_s = new User();
        user_s.setUsername("Genry Ford");
        user_s.setPassword("2");
        user_s.setRoles(roles_s);

        userService.save(user_s, seller);

        User user_u = new User();
        user_u.setUsername("Alex Mine");
        user_u.setPassword("1");
        user_u.setRoles(roles_u);

        userService.save(user_u, seller);

        Customer customer = customerService.findCustomerByName(user_u.getUsername());

        Detail detailFromDB = detailService.findDetailByName("ремень грм форд фокус");

        Order order = new Order();
        order.setQuantity(5);
        order.setCost(1863);
        order.setCustomer(customer);
        //order.setDate("12/11/2020");
        order.setDetail(detailFromDB);
        order.setStatus(0);

        orderService.save(order);
        Customer customer2 = customerService.findCustomerByName("Alex Mine");
       //orderService.deleteOrderByCustomer(customer2);
    }
}
