package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.SubscriptionController;
import org.example.evaluations.evaluation.dtos.*;
import org.example.evaluations.evaluation.services.ISubscriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISubscriptionService subscriptionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateSubscription() throws Exception {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest();
        request.setChargeCount(5L);
        request.setTotalNumberOfBillingCycles(12L);
        request.setStarting(1L);
        request.setEnding(12L);
        request.setPhone("1234567890");
        request.setEmail("test@example.com");
        request.setPlanPeriod(Period.monthly);
        request.setPlanTitle("Gold Plan");
        request.setPlanDescription("Premium subscription");
        request.setPlanAmount(99.99);

        String shortUrl = "https://rzp.io/i/m0y0f";

        when(subscriptionService.createSubscription(any(CreateSubscriptionRequest.class))).thenReturn(shortUrl);

        // Perform the test
        mockMvc.perform(post("/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(shortUrl));
    }
}
