package group.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/hello")
    public String greeting(Model model) {
        return "greetings";
    }

    @GetMapping("/")
    public String anotherGreeting(Model model) {
        return "redirect:/hello";
    }

    @GetMapping("/403")
    public String get403Page() {
        return "403";
    }
}
