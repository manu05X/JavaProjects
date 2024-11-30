package org.example.evaluations.services;

import com.stripe.param.PlanCreateParams;
import org.example.evaluations.evaluation.clients.StripePaymentGateway;
import org.example.evaluations.evaluation.services.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SubscriptionServiceTest {

    @Autowired
    private SubscriptionService subscriptionService;

    @MockBean
    private StripePaymentGateway stripePaymentGateway;

    @Test
    void createSubscriptionForProductTest() {
        // Arrange
        String customerName = "John Doe";
        String customerEmail = "john.doe@example.com";
        Long productAmount = 100L;
        String productName = "Product A";
        PlanCreateParams.Interval interval = PlanCreateParams.Interval.MONTH;
        String expectedResponse = "subs_12121hi21ih21";

        when(stripePaymentGateway.createSubscriptionForProduct(
                anyString(), anyString(), any(Long.class), anyString(), any()
        )).thenReturn(expectedResponse);

        // Act
        String actualResponse = subscriptionService.createSubscriptionForProduct(
                customerName, customerEmail, productAmount, productName, interval
        );

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }
}
