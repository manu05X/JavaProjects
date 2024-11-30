package org.example.evaluations.services;

import com.razorpay.Subscription;
import org.example.evaluations.evaluation.clients.RazorpayPaymentGatewayClient;
import org.example.evaluations.evaluation.dtos.CreateSubscriptionRequest;
import org.example.evaluations.evaluation.dtos.Period;
import org.example.evaluations.evaluation.dtos.RazorpaySubscriptionRequest;
import org.example.evaluations.evaluation.services.SubscriptionService;
import org.example.evaluations.evaluation.utils.SubscriptionRequestConverter;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SubscriptionServiceTest {
    @Autowired
    private SubscriptionService subscriptionService;

    @MockBean
    private RazorpayPaymentGatewayClient razorpayPaymentGatewayClient;

    @MockBean
    private SubscriptionRequestConverter subscriptionRequestConverter;

    @Test
    public void testCreateSubscription() {
        CreateSubscriptionRequest createSubscriptionRequest = new CreateSubscriptionRequest();
        createSubscriptionRequest.setChargeCount(5L);
        createSubscriptionRequest.setTotalNumberOfBillingCycles(12L);
        createSubscriptionRequest.setStarting(1L);
        createSubscriptionRequest.setEnding(12L);
        createSubscriptionRequest.setPhone("1234567890");
        createSubscriptionRequest.setEmail("test@example.com");
        createSubscriptionRequest.setPlanPeriod(Period.monthly);
        createSubscriptionRequest.setPlanTitle("Gold Plan");
        createSubscriptionRequest.setPlanDescription("Premium subscription");
        createSubscriptionRequest.setPlanAmount(99.99);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("short_url", "https://rzp.io/i/m0y0f");
        Subscription subscription = new Subscription(jsonObject);
        when(razorpayPaymentGatewayClient.createSubscriptionLink(any(RazorpaySubscriptionRequest.class)))
                .thenReturn(subscription);

        RazorpaySubscriptionRequest razorpaySubscriptionRequest = new RazorpaySubscriptionRequest();
        when(subscriptionRequestConverter.from(any(CreateSubscriptionRequest.class))).thenReturn(razorpaySubscriptionRequest);

        String result = subscriptionService.createSubscription(createSubscriptionRequest);

        assertEquals("https://rzp.io/i/m0y0f", result);
    }
}
