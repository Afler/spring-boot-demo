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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final SellerService sellerService;
    private final CustomerService customerService;
    private final List<String> roleList = Arrays.asList(Role.CUSTOMER.name(), Role.SELLER.name());

    @Autowired
    public AuthController(UserService userService, SellerService sellerService, CustomerService customerService, CustomerService customerService1) {
        this.userService = userService;
        this.sellerService = sellerService;
        this.customerService = customerService1;
    }

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("registration")
    public String getRegistrationPage(@ModelAttribute("user") User user,
                                      @ModelAttribute("seller") Seller seller,
                                      Model model) {
        model.addAttribute("roleList", roleList);
        model.addAttribute("sellerList", sellerService.findAll());

        return "registration";
    }

    @GetMapping("success")
    public String getSuccessPage() {
        return "success";
    }

    @PostMapping("registration")
    public String registerNewUser(@Valid @ModelAttribute("user") User user,
                                  @ModelAttribute("seller") Seller seller,
                                  BindingResult bindingResult,
                                  Model model) {
        User userFromDB = userService.findByUsername(user.getUsername());

        if (userFromDB != null) {
            model.addAttribute("message", "user exist");
            model.addAttribute("roleList", roleList);
            model.addAttribute("sellerList", sellerService.findAll());

            return "registration";
        } else if (bindingResult.hasErrors()) {
            model.addAttribute("roleList", roleList);
            model.addAttribute("sellerList", sellerService.findAll());

            return "registration";
        } else {
            String name = user.getUsername();
            if (user.getRoles().contains(Role.CUSTOMER)) {
                customerService.save(new Customer(name, user.getPassword(), true, Role.CUSTOMER, name, 0, seller));
            } else {
                sellerService.save(new Seller(name, user.getPassword(), true, Role.SELLER, name, 0));
            }

            return "redirect:/auth/login";
        }

    }
}
