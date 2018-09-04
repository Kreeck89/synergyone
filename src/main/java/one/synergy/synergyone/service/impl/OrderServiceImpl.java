package one.synergy.synergyone.service.impl;

import one.synergy.synergyone.model.Order;
import one.synergy.synergyone.repository.OrderRepository;
import one.synergy.synergyone.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findOrderByClientId(Long clientId) {
        return orderRepository.findByClientId(clientId);
    }

    @Override
    public Order createNewOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderStatus(Order order) {
        return orderRepository.saveAndFlush(order);
    }
}
