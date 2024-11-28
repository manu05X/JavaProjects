package org.example.evaluations.repositories;

import org.example.evaluations.evaluation.models.Order;
import org.example.evaluations.evaluation.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        Order order = new Order();
        order.setId(1L);
        order.setCustomerId(2L);
        order.setTotalAmount(1000D);
        orderRepository.save(order);

        Order order2 = new Order();
        order2.setId(10L);
        order2.setCustomerId(100L);
        order2.setTotalAmount(249D);
        orderRepository.save(order2);
    }

    @Test
    public void testFindByIdWithSavedOrder() {
        Order order = orderRepository.findById(1L);
        assertNotNull(order, "Order should not be null. Please get Order from HashMap using orderId");
        assertEquals(1L, order.getId(), "You are getting incorrect order from Hashmap.");
        assertEquals(1000D, order.getTotalAmount(), "You are getting incorrect order from Hashmap.");
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = orderRepository.findAll();
        assertNotNull(orders,"Hashmap contains 2 orders");
        assertEquals(1L,orders.get(0).getId(),"HashMap contain 2 orders and Id of first order is 1.");
        assertEquals(10L,orders.get(1).getId(),"HashMap contain 2 orders and Id of second order is 10");
    }

    @Test
    public void testFindByIdWithNonSavedOrder() {
        Order order = orderRepository.findById(99L);
        assertNull(order, "Order should be null as we have never saved order with ID 99L in Hashmap");
    }
}
