package group.springbootdemo.controller;

import group.springbootdemo.model.Detail;
import group.springbootdemo.model.Order;
import group.springbootdemo.model.User;
import group.springbootdemo.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final DetailService detailService;

    @Autowired
    public OrderController(DetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping("new")
    public String getNewOrderPage(Model model) {
        List<Detail> details = detailService.findAllDetails();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("detailList", details);
        model.addAttribute("customerId", user.getId());

        return "newOrder";
    }

}
