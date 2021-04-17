package group.springbootdemo.controller;

import group.springbootdemo.model.Detail;
import group.springbootdemo.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/detail")
public class DetailController {

    private final DetailService detailService;

    @Autowired
    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping
    @RequestMapping("list")
    public String gerDetailList(Model model){
        List<Detail> details = detailService.findAll();
        model.addAttribute("detailsList", details);
        return "detailList";
    }
}
