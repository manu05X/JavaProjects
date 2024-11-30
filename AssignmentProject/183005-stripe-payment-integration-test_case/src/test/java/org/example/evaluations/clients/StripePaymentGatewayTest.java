package org.example.evaluations.clients;

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
    void testGetPaymentLinkSuccess() {
        // Arrange
        Long amount = 350000L;
        Long quantity = 1L;
        String callbackUrl = "https://scaler.com";
        String productName = "Academy Backend Course";

        stripePaymentGateway.apiKey = apiKey;

        // Act
        String result = stripePaymentGateway.getPaymentLink(amount, quantity, callbackUrl, productName);

        // Assert
        assertNotNull(result);
    }
}
