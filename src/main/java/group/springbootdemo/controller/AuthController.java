package group.springbootdemo.controller;

import group.springbootdemo.model.Customer;
import group.springbootdemo.model.Role;
import group.springbootdemo.model.Seller;
import group.springbootdemo.model.User;
import group.springbootdemo.service.CustomerService;
import group.springbootdemo.service.SellerService;
import group.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    private SellerService sellerService;

    private CustomerService customerService;

    List<String> roleList = Arrays.asList(Role.USER.name(), Role.SELLER.name());

    @Autowired
    public AuthController(UserService userService, SellerService sellerService, CustomerService customerService) {
        this.userService = userService;
        this.sellerService = sellerService;
        this.customerService = customerService;
    }

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("seller", new Seller());

        model.addAttribute("roleList", roleList);
        model.addAttribute("sellerList", sellerService.findAllSellers());

        return "registration";
    }

    @GetMapping("success")
    public String getSuccessPage() {
        return "success";
    }

    @PostMapping("registration")
    public String registerNewUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @ModelAttribute("seller") Seller seller, Model model) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        if (userFromDB != null) {
            model.addAttribute("message", "user exist");
            model.addAttribute("roleList", roleList);
            model.addAttribute("sellerList", sellerService.findAllSellers());
            return "registration";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("roleList", roleList);
            model.addAttribute("sellerList", sellerService.findAllSellers());
            return "registration";
        }

        userService.saveUser(user, seller);
        return "redirect:/auth/login";
    }
}
