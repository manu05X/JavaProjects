package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.exceptions.OrderNotFoundException;

public interface IOrderService {
    Boolean cancelOrder(Long orderId) throws OrderNotFoundException;
}
