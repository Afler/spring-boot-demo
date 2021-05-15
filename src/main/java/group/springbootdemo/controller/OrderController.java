package group.springbootdemo.controller;

import group.springbootdemo.model.Customer;
import group.springbootdemo.model.Detail;
import group.springbootdemo.model.Order;
import group.springbootdemo.model.User;
import group.springbootdemo.service.CustomerService;
import group.springbootdemo.service.DetailService;
import group.springbootdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final DetailService detailService;

    private final CustomerService customerService;

    private final OrderService orderService;

    @Autowired
    public OrderController(DetailService detailService, CustomerService customerService, OrderService orderService) {
        this.detailService = detailService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("new")
    public String getNewOrderPage(Model model) {
        List<Detail> details = detailService.findAll();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("detailList", details);
        model.addAttribute("currentUsername", user.getUsername());

        return "newOrder";
    }

    @GetMapping("list")
    public String getOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        return "orderList";
    }

    @GetMapping("status")
    public String getStatus(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.findByName(user.getUsername());
        List<Order> orders = orderService.findAllByCustomer(customer);
        model.addAttribute("orders", orders);

        return "statusOrder";
    }

    @GetMapping("changeStatus/{id}")
    public String changeStatus(@PathVariable("id") int id) {
        Order order = orderService.findOrderById(id);
        int status = order.getStatus();
        if (status < 2) {
            order.setStatus(++status);
        } else {
           order.setStatus(0);
        }
        orderService.save(order);
        return "redirect:/order/list";
    }

}
