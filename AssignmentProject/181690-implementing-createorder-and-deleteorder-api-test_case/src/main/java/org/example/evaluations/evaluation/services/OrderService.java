package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Order;
import org.example.evaluations.evaluation.models.OrderStatus;
import org.example.evaluations.evaluation.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Long customerId, Double totalAmount) {
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.CREATED);
        order.setId(UUID.randomUUID());
        order.setCreatedAt(new Date());
        order.setLastUpdatedAt(new Date());
        orderRepository.save(order);
        return order;
    }

    @Override
    public Boolean deleteOrder(UUID orderId) {
        return orderRepository.remove(orderId);
    }
}