package org.example.evaluations.services;

import org.example.evaluations.evaluation.clients.StripePaymentGateway;
import org.example.evaluations.evaluation.dtos.SessionDto;
import org.example.evaluations.evaluation.services.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @MockBean
    private StripePaymentGateway stripePaymentGateway;

    @Test
    void testCreateSession() {
        // Arrange
        String successUrl = "http://success.url";
        List<Long> amounts = Arrays.asList(100L, 200L);
        List<String> productNames = Arrays.asList("Product1", "Product2");
        List<Long> quantities = Arrays.asList(1L, 2L);

        SessionDto expectedSessionDto = new SessionDto();
        expectedSessionDto.setTotal(500L);
        expectedSessionDto.setId("sess_id__2121_h8h3232");
        when(stripePaymentGateway.createSession(successUrl, amounts, productNames, quantities))
                .thenReturn(expectedSessionDto);

        // Act
        SessionDto actualSessionDto = sessionService.createSession(successUrl, amounts, productNames, quantities);

        // Assert
        verify(stripePaymentGateway).createSession(successUrl, amounts, productNames, quantities);
        assertEquals(expectedSessionDto, actualSessionDto);
    }


}
