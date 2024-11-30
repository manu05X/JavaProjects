package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.OrderController;
import org.example.evaluations.evaluation.dtos.CancelOrderRequestDto;
import org.example.evaluations.evaluation.exceptions.OrderNotFoundException;
import org.example.evaluations.evaluation.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;


@WebMvcTest(OrderController.class)
public class OrderControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCancelOrderSuccess() throws Exception {
        Long orderId = 1L;

        CancelOrderRequestDto cancelOrderRequestDto = new CancelOrderRequestDto();
        cancelOrderRequestDto.setOrderId(orderId);

        when(orderService.cancelOrder(orderId)).thenReturn(true);

        mockMvc.perform(delete("/order")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cancelOrderRequestDto)))
                .andExpect(status().isOk());

        verify(orderService, times(1)).cancelOrder(orderId);
    }

    @Test
    void testCancelOrderOrderNotFoundException() throws Exception {
        Long orderId = 1L;

        CancelOrderRequestDto cancelOrderRequestDto = new CancelOrderRequestDto();
        cancelOrderRequestDto.setOrderId(orderId);

        when(orderService.cancelOrder(orderId)).thenThrow(new OrderNotFoundException("Order not found"));

        mockMvc.perform(delete("/order")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cancelOrderRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Order not found"));

        verify(orderService, times(1)).cancelOrder(orderId);
    }
}
