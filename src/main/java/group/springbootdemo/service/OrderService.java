package group.springbootdemo.service;

import group.springbootdemo.model.Customer;
import group.springbootdemo.model.Order;
import group.springbootdemo.repository.CustomerRepository;
import group.springbootdemo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public void deleteOrderByCustomer(Customer customer) {
        orderRepository.deleteOrderByCustomer(customer);
    }

}
