package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.OrderController;
import org.example.evaluations.evaluation.models.Order;
import org.example.evaluations.evaluation.models.OrderStatus;
import org.example.evaluations.evaluation.services.IOrderService;
import org.example.evaluations.evaluation.dtos.OrderRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateOrderRunsSuccessfully() throws Exception {

        //Arrange
        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setCustomerId(2L);
        requestDto.setTotalAmount(69D);

        Order order = new Order();
        order.setCustomerId(2L);
        order.setTotalAmount(69D);
        order.setStatus(OrderStatus.CREATED);
        order.setId(UUID.randomUUID());
        order.setCreatedAt(new Date());
        order.setLastUpdatedAt(new Date());

        when(orderService.createOrder(2L,69D)).thenReturn(order);

        // Act & Assert
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(order)));
    }

    @Test
    public void testdeleteOrderRunsSuccessfully() throws Exception {
        //Arrange
        UUID orderId = UUID.randomUUID();
        when(orderService.deleteOrder(orderId)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/order/{orderId}",orderId))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
