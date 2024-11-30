package org.example.evaluations.services;

import org.example.evaluations.evaluation.clients.StripePaymentGateway;
import org.example.evaluations.evaluation.services.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private StripePaymentGateway stripePaymentGateway;

    @Test
    void testGetPaymentLinkSuccess() {
        // Arrange
        Long amount = 1000L;
        Long quantity = 1L;
        String callbackUrl = "http://example.com/callback";
        String productName = "Test Product";
        String expectedPaymentLink = "http://example.com/payment";

        when(stripePaymentGateway.getPaymentLink(amount, quantity, callbackUrl, productName))
                .thenReturn(expectedPaymentLink);

        // Act
        String result = paymentService.getPaymentLink(amount, quantity, callbackUrl, productName);

        // Assert
        assertEquals(expectedPaymentLink, result);
    }

    @Test
    void testGetPaymentLinkServiceFailure() {
        // Arrange
        Long amount = 1000L;
        Long quantity = 1L;
        String callbackUrl = "http://example.com/callback";
        String productName = "Test Product";

        when(stripePaymentGateway.getPaymentLink(amount, quantity, callbackUrl, productName))
                .thenThrow(new RuntimeException("Service failure"));

        // Act & Assert
        RuntimeException thrown = null;
        try {
            paymentService.getPaymentLink(amount, quantity, callbackUrl, productName);
        } catch (RuntimeException e) {
            thrown = e;
        }

        assertEquals("Service failure", thrown.getMessage());
    }
}
