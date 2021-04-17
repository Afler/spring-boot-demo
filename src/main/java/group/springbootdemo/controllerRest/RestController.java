package group.springbootdemo.controllerRest;

import group.springbootdemo.model.Customer;
import group.springbootdemo.model.Order;
import group.springbootdemo.service.CustomerService;
import group.springbootdemo.service.DetailService;
import group.springbootdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    private final DetailService detailService;

    private final CustomerService customerService;

    private final OrderService orderService;

    @Autowired
    public RestController(DetailService detailService, CustomerService customerService, OrderService orderService) {
        this.detailService = detailService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @PostMapping(value = "postOrder")
    public void someOrder(@RequestBody List<Order> orders, @RequestParam String username) {
        if (orders != null) {
            Customer customer = customerService.findCustomerByName(username);
            LocalDate date = LocalDate.now();

            if (customer != null) {
                orders.forEach(order -> {
                    String detailName = order.getDetail().getName();
                    order.setDetail(detailService.findDetailByName(detailName));
                    order.setCustomer(customer);
                    order.setDate(date);
                    orderService.save(order);
                });
            }
        }
    }
}
