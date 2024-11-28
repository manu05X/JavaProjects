package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Order;

import java.util.UUID;

public interface IOrderService {
    Order createOrder(Long customerId, Double totalAmount);

    Boolean deleteOrder(UUID orderId);
}