package group.springbootdemo.controller;

import group.springbootdemo.model.Customer;
import group.springbootdemo.model.Seller;
import group.springbootdemo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/seller")
@PreAuthorize("hasAuthority('SELLER')") //only users with SELLER role have access to /seller/** request
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("list")
    public String findAllSeller(Model model) {
        List<Seller> sellers = sellerService.findAll();
        model.addAttribute("sellers", sellers);
        return "sellerList";
    }

    @GetMapping("create")
    public String createSellerForm(@ModelAttribute("seller") Seller seller) {

        return "sellerCreate";
    }

    @PostMapping("create")
    public String createSeller(@Valid Seller seller, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "sellerCreate";

        sellerService.save(seller);
        return "redirect:/seller/list";
    }

    @GetMapping("delete/{id}")
    public String deleteSeller(@PathVariable("id") int id) {
        sellerService.deleteById(id);
        return "redirect:/seller/list";
    }

    @GetMapping("update/{id}")
    public String getUpdateSellerForm(@PathVariable("id") int id, Model model) {
        Seller seller = sellerService.findById(id);
        model.addAttribute("seller", seller);
        return "sellerUpdate";
    }

    @PostMapping("update")
    public String updateSeller(@Valid Seller seller, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "sellerUpdate";

        sellerService.save(seller);
        return "redirect:/seller/list";
    }

    @GetMapping("customers/{id}")
    public String getSellerCustomers(@PathVariable("id") int id, Model model) {
        List<Customer> customers = sellerService.findById(id).getCustomers();
        model.addAttribute("customers", customers);
        return "customerList";
    }
}
