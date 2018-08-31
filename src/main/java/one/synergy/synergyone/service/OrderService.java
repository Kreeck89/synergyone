package one.synergy.synergyone.service;

import one.synergy.synergyone.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    List<Order> findOrderByClientId(Long clientId);

    Order createNewOrder(Order order);

    Order updateOrderStatus(Order order);
}
