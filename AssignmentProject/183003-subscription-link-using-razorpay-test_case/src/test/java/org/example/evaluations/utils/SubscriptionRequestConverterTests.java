package org.example.evaluations.utils;

import org.example.evaluations.evaluation.dtos.CreateSubscriptionRequest;
import org.example.evaluations.evaluation.dtos.Period;
import org.example.evaluations.evaluation.dtos.RazorpaySubscriptionRequest;
import org.example.evaluations.evaluation.utils.SubscriptionRequestConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SubscriptionRequestConverterTests {

    @Autowired
    private SubscriptionRequestConverter requestConverter;

    @Test
    public void testFrom() {
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

        // Convert the request
        RazorpaySubscriptionRequest razorpaySubscriptionRequest = requestConverter.from(createSubscriptionRequest);

        // Assert the conversion results
        assertEquals(createSubscriptionRequest.getEnding(), razorpaySubscriptionRequest.getExpiryTime());
        assertEquals(createSubscriptionRequest.getStarting(), razorpaySubscriptionRequest.getStartTime());
        assertEquals(createSubscriptionRequest.getChargeCount(), razorpaySubscriptionRequest.getQuantity());
        assertEquals(createSubscriptionRequest.getTotalNumberOfBillingCycles(), razorpaySubscriptionRequest.getTotalCount());

        assertEquals(createSubscriptionRequest.getPhone(), razorpaySubscriptionRequest.getRazorpayCustomerContactDetails().getPhoneNumber());
        assertEquals(createSubscriptionRequest.getEmail(), razorpaySubscriptionRequest.getRazorpayCustomerContactDetails().getEmail());

        assertEquals(createSubscriptionRequest.getPlanPeriod().name(), razorpaySubscriptionRequest.getRazorpayPlanRequest().getFrequency());
        assertEquals(createSubscriptionRequest.getPlanTitle(), razorpaySubscriptionRequest.getRazorpayPlanRequest().getName());
        assertEquals(createSubscriptionRequest.getPlanDescription(), razorpaySubscriptionRequest.getRazorpayPlanRequest().getDescription());
        assertEquals(createSubscriptionRequest.getPlanAmount(), razorpaySubscriptionRequest.getRazorpayPlanRequest().getAmount());
    }
}
