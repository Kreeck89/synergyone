package one.synergy.synergyone.controller;

import one.synergy.synergyone.model.Order;
import one.synergy.synergyone.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/all")
    public List<Order> getAll() {
        List<Order> all = orderService.findAll();
        return orderService.findAll();
    }

    @GetMapping(value = "/byClientId/{id}")
    public List<Order> byClientId(@PathVariable("id") Long id) {
        return orderService.findOrderByClientId(id);
    }

    @PostMapping(value = "/createNew")
    public Order createNewOrder(@RequestBody Order order) {
        return orderService.createNewOrder(order);
    }

    @PatchMapping(value = "/updateStatus")
    public Order updateOrderStatus(@RequestBody Order order) {
        return orderService.updateOrderStatus(order);
    }
}
