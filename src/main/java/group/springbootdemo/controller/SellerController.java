package group.springbootdemo.controller;

import group.springbootdemo.model.Customer;
import group.springbootdemo.model.Seller;
import group.springbootdemo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/sellers")
    public String findAllSeller(Model model) {
        List<Seller> sellers = sellerService.findAllSellers();
        model.addAttribute("sellers", sellers);
        return "sellerList";
    }

    @GetMapping("/seller_create")
    public String createSellerForm(Seller seller) {
        return "sellerCreate";
    }

    @PostMapping("/seller_create")
    public String createSeller(@Valid Seller seller, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "sellerCreate";

        sellerService.saveSeller(seller);
        return "redirect:/sellers";
    }

    @GetMapping("/seller_delete/{id}")
    public String deleteSeller(@PathVariable("id") int id) {
        sellerService.deleteSellerById(id);
        return "redirect:/sellers";
    }

    @GetMapping("/seller_update/{id}")
    public String updateSellerForm(@PathVariable("id") int id, Model model) {
        Seller seller = sellerService.findSellerById(id);
        model.addAttribute("seller", seller);
        return "sellerUpdate";
    }

    @PostMapping("/seller_update")
    public String updateSeller(@Valid Seller seller, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "sellerUpdate";

        sellerService.saveSeller(seller);
        return "redirect:/sellers";
    }

    @GetMapping("sel_customers/{id}")
    public String getSellerCustomers(@PathVariable("id") int id, Model model) {
        List<Customer> customers = sellerService.findSellerById(id).getCustomers();
        model.addAttribute("customers", customers);
        return "customerList";
    }
}
