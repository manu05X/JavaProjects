package org.example.evaluations.clients;

import org.example.evaluations.evaluation.clients.StripePaymentGateway;
import org.example.evaluations.evaluation.dtos.SessionDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class StripePaymentGatewayTest {

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    @Value("${fromTest}")
    private String apiKey;

    @Test
    void testCreateSession() {
        // Arrange
        List<Long> amounts = new ArrayList<Long>();
        List<Long> quantities = new ArrayList<Long>();
        List<String> productNames = new ArrayList<>();
        amounts.add(350000L);
        amounts.add(15000L);
        quantities.add(2L);
        quantities.add(3L);
        productNames.add("GoldPlan");
        productNames.add("SilverPlan");
        String successUrl = "https://scaler.com";

        stripePaymentGateway.apiKey = apiKey;

        // Act
        SessionDto sessionDto = stripePaymentGateway.createSession(successUrl,amounts,productNames,quantities);
        // Assert
        assertNotNull(sessionDto);
        assertEquals(745000,sessionDto.getTotal());
    }
}
