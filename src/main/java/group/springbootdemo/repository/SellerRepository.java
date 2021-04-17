package group.springbootdemo.repository;

import group.springbootdemo.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring data automatically create implement of the interface
public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
