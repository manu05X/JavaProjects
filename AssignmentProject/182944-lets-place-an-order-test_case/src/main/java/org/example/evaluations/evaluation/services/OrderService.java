package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.exceptions.ShortInventoryException;
import org.example.evaluations.evaluation.models.*;
import org.example.evaluations.evaluation.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemDetailRepo itemDetailRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderStateTimeMappingRepo orderStateTimeMappingRepo;

    public Order createOrder(Map<Long,Long> itemQuantityMap, Long customerId) throws ShortInventoryException {
        Order order = new Order();
        Order persistedOrder = orderRepo.save(order);

        Double totalCost = 0D;
        for(Map.Entry<Long,Long> mapElement : itemQuantityMap.entrySet()) {
            Long productId = mapElement.getKey();
            Long quantity = mapElement.getValue();
            Item item = itemRepo.findById(productId).get();
            ItemDetail itemDetail = new ItemDetail();
            itemDetail.setItem(item);
            itemDetail.setQuantity(quantity);
            itemDetail.setOrder(persistedOrder);
            Inventory inventory = inventoryRepo.findByItem(item).get();
            Double count = inventory.getCount();
            if(count < quantity) {
                throw new ShortInventoryException("Ordered Quantity is not Available");
            }
            inventory.setCount(count-quantity);
            totalCost = totalCost + (item.getPrice() * quantity);
            inventoryRepo.save(inventory);
            itemDetailRepo.save(itemDetail);
        }

        OrderStateTimeMapping orderStateTimeMapping = new OrderStateTimeMapping();
        orderStateTimeMapping.setOrder(persistedOrder);
        orderStateTimeMappingRepo.save(orderStateTimeMapping);

        Customer customer = customerRepo.findById(customerId).get();
        persistedOrder.setCustomer(customer);
        persistedOrder.setTotalCost(totalCost);
        persistedOrder = orderRepo.save(persistedOrder);
        return persistedOrder;
    }
}
