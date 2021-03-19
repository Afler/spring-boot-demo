package group.springbootdemo.controller;

import group.springbootdemo.model.Customer;
import group.springbootdemo.model.Seller;
import group.springbootdemo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/seller")
@PreAuthorize("hasAuthority('SELLER')")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("sellers")
    public String findAllSeller(Model model) {
        List<Seller> sellers = sellerService.findAllSellers();
        model.addAttribute("sellers", sellers);
        return "sellerList";
    }

    @GetMapping("create")
    public String createSellerForm(Seller seller) {
        return "sellerCreate";
    }

    @PostMapping("create")
    public String createSeller(@Valid Seller seller, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "sellerCreate";

        sellerService.saveSeller(seller);
        return "redirect:/seller/sellers";
    }

    @GetMapping("delete/{id}")
    public String deleteSeller(@PathVariable("id") int id) {
        sellerService.deleteSellerById(id);
        return "redirect:/seller/sellers";
    }

    @GetMapping("update/{id}")
    public String updateSellerForm(@PathVariable("id") int id, Model model) {
        Seller seller = sellerService.findSellerById(id);
        model.addAttribute("seller", seller);
        return "sellerUpdate";
    }

    @PostMapping("update")
    public String updateSeller(@Valid Seller seller, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "sellerUpdate";

        sellerService.saveSeller(seller);
        return "redirect:/seller/sellers";
    }

    @GetMapping("customers/{id}")
    public String getSellerCustomers(@PathVariable("id") int id, Model model) {
        List<Customer> customers = sellerService.findSellerById(id).getCustomers();
        model.addAttribute("customers", customers);
        return "customerList";
    }
}
