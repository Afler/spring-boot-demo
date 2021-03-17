package group.springbootdemo;

import group.springbootdemo.model.Auto;
import group.springbootdemo.model.User;
import group.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringbootdemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);

    }

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {


//        User ivan = new User();
//        ivan.setAge(30);
//        ivan.setName("Vitya");
//
//
//        Auto auto1 = new Auto();
//        auto1.setColor("blue");
//        auto1.setModel("Lamborghini");
//
//        ivan.setAutoList(Arrays.asList(auto1));
//
//        userRepository.save(ivan);
//
//
//
//        userRepository.deleteById(18);
    }

}
