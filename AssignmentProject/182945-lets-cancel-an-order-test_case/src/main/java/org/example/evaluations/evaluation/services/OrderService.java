package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.exceptions.OrderNotFoundException;
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
    private InventoryRepo inventoryRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderStateTimeMappingRepo orderStateTimeMappingRepo;

    public Boolean cancelOrder(Long orderId) throws OrderNotFoundException {
        // Step 1: Fetch the order from the database
        Optional<Order> orderOptional = orderRepo.findById(orderId);
        if(orderOptional.isEmpty()) {
            throw new OrderNotFoundException("orderId is wrong");
        }

        // Step 2: Fetch and delete all ItemDetails for the order
        Order order = orderOptional.get();
        List<ItemDetail> itemDetails = itemDetailRepo.findByOrder(order); //itemDetails is not null
        for(ItemDetail itemDetail : itemDetails) {
            Item item = itemDetail.getItem(); //item is not null
            // Step 2.1: Update inventory for each item
            Inventory inventory = inventoryRepo.findByItem(item).get();
            Double count = inventory.getCount();
            inventory.setCount(count+itemDetail.getQuantity());
            inventoryRepo.save(inventory);

            // Step 2.2: Delete the ItemDetail
            itemDetailRepo.deleteById(itemDetail.getId());
        }

        // Step 3: Create a new OrderStateTimeMapping with CANCELLED state
        OrderStateTimeMapping orderStateTimeMapping = new OrderStateTimeMapping();
        orderStateTimeMapping.setOrderState(OrderState.CANCELLED);
        orderStateTimeMapping.setOrder(order);
        orderStateTimeMappingRepo.save(orderStateTimeMapping);

        // Step 4: Update Customer's orderCancellationCount
        Customer customer = order.getCustomer();
        Long cancellationCount = customer.getOrderCancellationCount();
        customer.setOrderCancellationCount(cancellationCount+1);
        customerRepo.save(customer);
        
        // Step 5: Return true to indicate successful cancellation
        return true;
    }
}
