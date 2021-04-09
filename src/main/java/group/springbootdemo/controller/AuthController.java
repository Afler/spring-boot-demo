package group.springbootdemo.controller;

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

    private UserService userService;

    private SellerService sellerService;

    private List<String> roleList = Arrays.asList(Role.CUSTOMER.name(), Role.SELLER.name());

    @Autowired
    public AuthController(UserService userService, SellerService sellerService, CustomerService customerService) {
        this.userService = userService;
        this.sellerService = sellerService;
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
        model.addAttribute("sellerList", sellerService.findAllSellers());

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
