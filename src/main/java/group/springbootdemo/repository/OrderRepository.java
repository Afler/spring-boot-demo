package group.springbootdemo.repository;

import group.springbootdemo.model.Customer;
import group.springbootdemo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    void deleteOrderByCustomer(Customer customer);

}
