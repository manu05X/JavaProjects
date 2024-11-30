package org.example.evaluations.services;

import org.example.evaluations.evaluation.exceptions.ShortInventoryException;
import org.example.evaluations.evaluation.models.*;
import org.example.evaluations.evaluation.repos.*;
import org.example.evaluations.evaluation.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceIntegrationTest {
    @Autowired
    private OrderService orderService;

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

    @Test
    void testCreateOrderSuccess() throws ShortInventoryException {
        //Arrange
        Long customerId = 1L;
        Long itemId = 1L;
        Long quantity = 2L;

        Customer customer = new Customer();
        customer.setId(customerId);
        customerRepo.save(customer);

        Item item = new Item();
        item.setId(itemId);
        item.setPrice(10.0);
        itemRepo.save(item);

        Inventory inventory = new Inventory();
        inventory.setCount(10.0);
        inventory.setItem(item);
        Inventory persistedInventory = inventoryRepo.save(inventory);

        Map<Long, Long> itemQuantityMap = new HashMap<>();
        itemQuantityMap.put(itemId, quantity);

        //Act
        Order result = orderService.createOrder(itemQuantityMap, customerId);

        //Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(20.0, result.getTotalCost());
        assertEquals(1L,result.getCustomer().getId());
        Inventory finalInventory = inventoryRepo.findById(persistedInventory.getId()).get();
        assertEquals(8.0,finalInventory.getCount());
        Optional<OrderStateTimeMapping> orderStateTimeMapping = orderStateTimeMappingRepo.findByOrder(result);
        assertTrue(orderStateTimeMapping.isPresent());
        assertEquals(OrderState.CONFIRMED,orderStateTimeMapping.get().getOrderState());
        Optional<ItemDetail> itemDetail = itemDetailRepo.findByOrder(result);
        assertTrue(itemDetail.isPresent());
        assertEquals(1L,itemDetail.get().getItem().getId());
        assertEquals(2L,itemDetail.get().getQuantity());
        Optional<Order> order = orderRepo.findById(result.getId());
        assertTrue(order.isPresent());
    }

    @Test
    void testCreateOrderThrowsShortInventoryException() throws ShortInventoryException {
        //Arrange
        Long customerId = 2L;
        Long itemId = 2L;
        Long quantity = 2L;

        Customer customer = new Customer();
        customer.setId(customerId);
        customerRepo.save(customer);

        Item item = new Item();
        item.setId(itemId);
        item.setPrice(10.0);
        itemRepo.save(item);

        Inventory inventory = new Inventory();
        inventory.setCount(1.0);
        inventory.setItem(item);
        inventoryRepo.save(inventory);

        Map<Long, Long> itemQuantityMap = new HashMap<>();
        itemQuantityMap.put(itemId, quantity);

        //Act and Assert
        assertThrows(ShortInventoryException.class, () -> {
            orderService.createOrder(itemQuantityMap, customerId);
        });
    }
}
