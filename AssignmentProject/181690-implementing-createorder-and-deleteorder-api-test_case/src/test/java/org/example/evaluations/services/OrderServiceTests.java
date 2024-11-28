package org.example.evaluations.services;

import org.example.evaluations.evaluation.models.Order;
import org.example.evaluations.evaluation.models.OrderStatus;
import org.example.evaluations.evaluation.repositories.OrderRepository;
import org.example.evaluations.evaluation.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTests {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    void testCreateOrder() {
        // Arrange
        Long customerId = 1L;
        Double totalAmount = 100.0;

        // Act
        Order createdOrder = orderService.createOrder(customerId, totalAmount);

        // Assert
        assertEquals(customerId, createdOrder.getCustomerId(),"Please set customerId passed in request.");
        assertEquals(totalAmount, createdOrder.getTotalAmount(),"Please set totalAmount passed in request.");
        assertEquals(OrderStatus.CREATED, createdOrder.getStatus(),"Order Status should be set to CREATED in createOrder method.");
        assertNotNull(createdOrder.getId(),"Order Id should not be null, Try generating random UUID and assigning it as Id.");
        assertNotNull(createdOrder.getCreatedAt(),"Order.createdAt should be set as soon as it is created");
        assertNotNull(createdOrder.getLastUpdatedAt(),"Order.lastUpdatedAt should be set as soon as it is created");
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void testdeleteOrder() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        when(orderRepository.remove(orderId)).thenReturn(true);

        // Act
        Boolean result = orderService.deleteOrder(orderId);

        // Assert
        assertEquals(true,result,"OrderService should call remove method of OrderRepository and return it's result.");
    }
}
