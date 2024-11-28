package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Order;

import java.util.List;

public interface IOrderService {
    Order getOrderById(Long orderId);

    List<Order> getAllOrders();
}
