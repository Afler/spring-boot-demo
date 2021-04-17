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

    public Seller findById(int id) {
        return sellerRepository.findById(id).orElse(null);
    }

    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    public void save(Seller seller) {
        sellerRepository.save(seller);
    }

    public void deleteById(int id) {
        sellerRepository.deleteById(id);
    }

}
