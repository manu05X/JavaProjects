package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.SubscriptionController;
import org.example.evaluations.evaluation.dtos.BillingFrequency;
import org.example.evaluations.evaluation.dtos.SubscriptionRequestDto;
import org.example.evaluations.evaluation.services.ISubscriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ISubscriptionService subscriptionService;

    @Test
    void createSubscriptionTest() throws Exception {
        // Arrange
        SubscriptionRequestDto requestDto = new SubscriptionRequestDto();
        requestDto.setCustomerName("John Doe");
        requestDto.setCustomerEmail("john.doe@example.com");
        requestDto.setProductAmount(100L);
        requestDto.setProductName("Product A");
        requestDto.setBillingFrequency(BillingFrequency.MONTH);

        String response = "subs_33131hihi322ojo23";
        when(subscriptionService.createSubscriptionForProduct(
                anyString(), anyString(), anyLong(), anyString(), any()
        )).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/subscription")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }
}
