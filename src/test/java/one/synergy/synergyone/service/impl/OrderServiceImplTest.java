package one.synergy.synergyone.service.impl;

import one.synergy.synergyone.model.*;
import one.synergy.synergyone.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    private final String MSG = "Maybe method was changed.";

    @Mock
    private OrderRepository orderRepository;
    private OrderServiceImpl orderService;
    private Order order;
    private Client client;

    @Before
    public void setUp() throws Exception {
        orderService = new OrderServiceImpl(orderRepository);

        Long id = 1L;
        client = new Client();
        client.setName("Alex");
        client.setSurname("Jush");
        client.setSex(Sex.MAN);
        client.setBirthday(LocalDate.of(1990, 2, 12));
        client.setItn(123456);
        client.setRoles(Collections.singleton(Role.USER));
        client.setId(id);

        order = new Order();
        order.setId(1L);
        order.setValue(12.12);
        order.setStatus(OrderStatus.inProcessing);
        order.setCurrency(Currency.UAH);
        order.setClient(client);
        order.setDate(LocalDate.now());
    }

    @Test
    public void entitiesNotNull() throws Exception {
        assertNotNull(client);
        assertNotNull(order);
        assertNotNull(orderRepository);
    }

    @Test
    public void findAllIsCalled() throws Exception {
        orderService.findAll();
        verify(orderRepository).findAll();
    }

    @Test
    public void findAllReturnIsNull() throws Exception {
        assertTrue(MSG, orderService.findAll().isEmpty());
    }

    @Test
    public void findOrderByClientIdIsCalled() throws Exception {
        orderService.findOrderByClientId(2L);
        verify(orderRepository).findByClientId(2L);
    }

    @Test
    public void findOrderByClientIdNotNull() throws Exception {
        assertTrue(MSG, orderService.findOrderByClientId(2L).isEmpty());
    }

    @Test
    public void findOrderByClientIdIsEmptyIfNotFound() throws Exception {
        List<Order> orderByClientId = orderService.findOrderByClientId(0L);
        assertTrue(MSG, orderByClientId.isEmpty());
    }

    @Test
    public void createNewOrderIsCalled() {
        orderService.createNewOrder(order);
        verify(orderRepository).save(order);
    }

    @Test
    public void createNewOrderNotReturnedOrderIfNotSave() {
        assertNull(MSG, orderService.createNewOrder(order));
    }

    @Test
    public void updateOrderStatusIsCalled() {
        orderService.updateOrderStatus(order);
        verify(orderRepository).saveAndFlush(order);
    }
}