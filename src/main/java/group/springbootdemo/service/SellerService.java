package group.springbootdemo.service;

import group.springbootdemo.model.Seller;
import group.springbootdemo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller findSellerById(int id) {
        return sellerRepository.findById(id).orElse(null);
    }

    public List<Seller> findAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller saveSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public void deleteSellerById(int id) {
        sellerRepository.deleteById(id);
    }

}
