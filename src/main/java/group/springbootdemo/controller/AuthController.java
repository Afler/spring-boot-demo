package group.springbootdemo.controller;

import group.springbootdemo.model.Role;
import group.springbootdemo.model.User;
import group.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("registration")
    public String getRegistrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        List<String> roleList = Arrays.asList(Role.USER.name(), Role.SELLER.name());
        model.addAttribute("roleList", roleList);

        return "registration";
    }

    @GetMapping("success")
    public String getSuccessPage() {
        return "success";
    }

    @PostMapping("registration")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            model.addAttribute("message", "user exist");
            return "registration";
        }

        user.setActive(true);
        userRepository.save(user);

        return "redirect:/auth/login";
    }
}
