package org.example.evaluations.services;

import org.example.evaluations.evaluation.models.Order;
import org.example.evaluations.evaluation.models.OrderStatus;
import org.example.evaluations.evaluation.repositories.OrderRepository;
import org.example.evaluations.evaluation.services.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    public void testGetOrderByIdWithSavedOrder() {
        // Arrange
        Order order = new Order();
        order.setId(5L);
        order.setStatus(OrderStatus.COMPLETED);
        when(orderRepository.findById(5L)).thenReturn(order);

        // Act
        Order result = orderService.getOrderById(5L);

        // Assert
        assertNotNull(result, "Order should not be null.");
        assertEquals(5L, result.getId(), "You have got incorrect order. You need to get order from Order Repository which will internally get order from Hashmap.");
        assertEquals(OrderStatus.COMPLETED,result.getStatus(),"You have got incorrect order. Order status is not matching.");
        verify(orderRepository).findById(idCaptor.capture());
        assertEquals(5L,idCaptor.getValue(),"Please call orderRepo.findById with same orderId.");
    }

    @Test
    public void testGetOrderByIdWithNonSavedOrder() {
        // Arrange
        when(orderRepository.findById(99L)).thenReturn(null);

        // Act
        Order result = orderService.getOrderById(99L);

        // Assert
        assertNull(result, "Order with ID 99L should be null.");
    }
}
