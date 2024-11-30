package org.example.evaluations.services;

import org.example.evaluations.evaluation.exceptions.OrderNotFoundException;
import org.example.evaluations.evaluation.models.*;
import org.example.evaluations.evaluation.repos.*;
import org.example.evaluations.evaluation.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    void testCancelOrderSuccess() throws OrderNotFoundException {
        //Arrange
        Customer customer = new Customer();
        customer.setOrderCancellationCount(3L);
        Long customerId = 1L;
        customer.setId(customerId);
        Customer persistedCustomer = customerRepo.save(customer);

        Order order = new Order();
        order.setCustomer(persistedCustomer);
        order.setTotalCost(15000D);
        Order persistedOrder = orderRepo.save(order);

        Long quantity1 = 30L;
        Item item1 = new Item();
        item1.setPrice(100.0);
        Item persistedItem1 = itemRepo.save(item1);

        Long quantity2 = 10L;
        Item item2 = new Item();
        item2.setPrice(1200.0);
        Item persistedItem2 = itemRepo.save(item2);

        ItemDetail itemDetail1 = new ItemDetail();
        itemDetail1.setOrder(persistedOrder);
        itemDetail1.setItem(persistedItem1);
        itemDetail1.setQuantity(quantity1);
        ItemDetail persistedItemDetail1 = itemDetailRepo.save(itemDetail1);

        ItemDetail itemDetail2 = new ItemDetail();
        itemDetail2.setOrder(persistedOrder);
        itemDetail2.setItem(persistedItem2);
        itemDetail2.setQuantity(quantity2);
        ItemDetail persistedItemDetail2 = itemDetailRepo.save(itemDetail2);

        Inventory inventory1 = new Inventory();
        inventory1.setCount(100D);
        inventory1.setItem(persistedItem1);
        Inventory persistedInventory1 = inventoryRepo.save(inventory1);

        Inventory inventory2 = new Inventory();
        inventory2.setCount(5D);
        inventory2.setItem(persistedItem2);
        Inventory persistedInventory2 = inventoryRepo.save(inventory2);

        //Act
        Boolean result = orderService.cancelOrder(persistedOrder.getId());

        //Assert
        assertTrue(result);

        Inventory finalInventory1 = inventoryRepo.findById(persistedInventory1.getId()).get();
        assertEquals(130.0,finalInventory1.getCount());

        Inventory finalInventory2 = inventoryRepo.findById(persistedInventory2.getId()).get();
        assertEquals(15.0,finalInventory2.getCount());

        List<ItemDetail> itemDetailList = itemDetailRepo.findByOrder(persistedOrder);
        assertTrue(itemDetailList.isEmpty());


        List<OrderStateTimeMapping> orderStateTimeMappings = orderStateTimeMappingRepo.findByOrder(persistedOrder);
        assertFalse(orderStateTimeMappings.isEmpty());
        assertEquals(OrderState.CANCELLED,orderStateTimeMappings.get(0).getOrderState());

        Customer customer1 = customerRepo.findById(persistedCustomer.getId()).get();
        assertEquals(4L,customer1.getOrderCancellationCount());
    }

    @Test
    void testCancelOrderThrowsOrderNotFoundException() throws OrderNotFoundException {
        //Act and Assert
        assertThrows(OrderNotFoundException.class, () -> {
            orderService.cancelOrder(2L);
        });
    }
}
