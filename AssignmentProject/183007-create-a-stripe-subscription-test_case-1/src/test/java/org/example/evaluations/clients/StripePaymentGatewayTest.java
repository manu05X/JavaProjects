package org.example.evaluations.clients;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import com.stripe.param.PlanCreateParams;
import com.stripe.param.SubscriptionCancelParams;
import org.example.evaluations.evaluation.clients.StripePaymentGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class StripePaymentGatewayTest {

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    @Value("${fromTest}")
    private String apiKey;

    @Test
    void testCreateSubscriptionForProduct() {
        // Arrange
        stripePaymentGateway.apiKey = apiKey;

        // Act
        String subscriptionId = stripePaymentGateway.createSubscriptionForProduct("example123","example123@gmail.com",1L,"gold plan", PlanCreateParams.Interval.MONTH);

        // Assert
        assertNotNull(subscriptionId);
        try {
            Subscription resource = Subscription.retrieve(subscriptionId);
            String customerId  = resource.getCustomer();
            SubscriptionCancelParams params = SubscriptionCancelParams.builder().build();
            resource.cancel(params);
            Customer customer = Customer.retrieve(customerId);
            customer.delete();
        }catch (StripeException exception) {
            throw new RuntimeException("It's not you, It's us. Something went wrong while cancelling subscription and deleting customer. Please contact Anurag Khanna.");
        }
    }
}
