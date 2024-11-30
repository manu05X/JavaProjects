package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.exceptions.ShortInventoryException;
import org.example.evaluations.evaluation.models.Order;

import java.util.Map;

public interface IOrderService {
    Order createOrder(Map<Long,Long> itemQuantityMap, Long customerId) throws ShortInventoryException;
}
