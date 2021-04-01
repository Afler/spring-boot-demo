package group.springbootdemo.controllerRest;

import group.springbootdemo.model.Order;
import group.springbootdemo.model.User;
import group.springbootdemo.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    private final DetailService detailService;

    @Autowired
    public RestController(DetailService detailService) {
        this.detailService = detailService;
    }

    @PostMapping("postSomeStr")
    public void someStr(@RequestBody String str) {
        System.out.println(str);

        int a = 3;
    }

    @PostMapping(value = "postOrder")
    public void someOrder(@RequestBody List<Order> orders) {
        List<Order> orderList = orders;

        // POST-request is sended from java-script code which have no authentication
        // Need to send username with orders
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        if (authentication != null) {
//            User user = (User) authentication.getPrincipal();
//        }
        orderList.forEach(order -> {
            order.setDetail(detailService.findDetailByName(order.getDetail().getName()));
            //order.setCustomer();
        });

        int a = 3;
    }

    @GetMapping("getName")
    @ResponseBody
    public String getName() {
        int b = 3;
        return "username";
    }
}
