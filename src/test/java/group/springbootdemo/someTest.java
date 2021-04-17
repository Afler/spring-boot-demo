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

    }
}
