package group.springbootdemo.service;

import group.springbootdemo.model.Customer;
import group.springbootdemo.model.Order;
import group.springbootdemo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllByCustomer(Customer customer) {
        return orderRepository.findAllByCustomer(customer);
    }

    public Order findOrderById(int id){return orderRepository.findOrderById(id);}

    public void save(Order order) {
        orderRepository.save(order);
    }

}
