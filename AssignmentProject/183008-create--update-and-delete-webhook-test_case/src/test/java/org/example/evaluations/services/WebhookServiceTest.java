package org.example.evaluations.services;

import org.example.evaluations.evaluation.clients.StripePaymentGateway;
import org.example.evaluations.evaluation.dtos.Webhook;
import org.example.evaluations.evaluation.dtos.WebhookStatus;
import org.example.evaluations.evaluation.services.WebhookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;

@SpringBootTest
public class WebhookServiceTest {

    @Autowired
    private WebhookService webhookService;

    @MockBean
    private StripePaymentGateway stripePaymentGateway;


    @Test
    public void testCreateWebhook() {
        // Arrange
        String url = "https://example.com/webhook";
        List<String> events = Collections.singletonList("event");

        // Set up expected Webhook object
        Webhook expectedWebhook = new Webhook();
        expectedWebhook.setId("webhook-id");
        expectedWebhook.setSecret("secret");
        expectedWebhook.setEvents(events);
        expectedWebhook.setUrl(url);
        expectedWebhook.setStatus(WebhookStatus.enabled);

        // Mock StripePaymentGateway response
        when(stripePaymentGateway.createWebhook(url, events)).thenReturn(expectedWebhook);

        // Act
        Webhook actualWebhook = webhookService.createWebhook(url, events);

        // Assert
        assertNotNull(actualWebhook);
        assertEquals(expectedWebhook.getId(), actualWebhook.getId());
        assertEquals(expectedWebhook.getSecret(), actualWebhook.getSecret());
        assertEquals(expectedWebhook.getEvents(), actualWebhook.getEvents());
        assertEquals(expectedWebhook.getUrl(), actualWebhook.getUrl());
        assertEquals(expectedWebhook.getStatus(), actualWebhook.getStatus());
        verify(stripePaymentGateway).createWebhook(url, events);
    }

    @Test
    public void testDeleteWebhook() {
        // Arrange
        String webhookId = "webhook-id";
        Boolean expectedResult = true;
        when(stripePaymentGateway.deleteWebhook(webhookId)).thenReturn(expectedResult);

        // Act
        Boolean actualResult = webhookService.deleteWebhook(webhookId);

        // Assert
        assertEquals(expectedResult, actualResult);
        verify(stripePaymentGateway).deleteWebhook(webhookId);
    }

    @Test
    public void testUpdateWebhook() {
        // Arrange
        String updatedUrl = "https://example.com/updated-webhook";
        List<String> events = Collections.singletonList("event");
        String webhookId = "webhook-id";

        // Set up expected Webhook object
        Webhook expectedWebhook = new Webhook();
        expectedWebhook.setId(webhookId);
        expectedWebhook.setSecret("new-secret");
        expectedWebhook.setEvents(events);
        expectedWebhook.setUrl(updatedUrl);
        expectedWebhook.setStatus(WebhookStatus.disabled);

        // Mock StripePaymentGateway response
        when(stripePaymentGateway.updateWebhook(updatedUrl, events, webhookId)).thenReturn(expectedWebhook);

        // Act
        Webhook actualWebhook = webhookService.updateWebhook(updatedUrl, events, webhookId);

        // Assert
        assertNotNull(actualWebhook);
        assertEquals(expectedWebhook.getId(), actualWebhook.getId());
        assertEquals(expectedWebhook.getSecret(), actualWebhook.getSecret());
        assertEquals(expectedWebhook.getEvents(), actualWebhook.getEvents());
        assertEquals(expectedWebhook.getUrl(), actualWebhook.getUrl());
        assertEquals(expectedWebhook.getStatus(), actualWebhook.getStatus());
        verify(stripePaymentGateway).updateWebhook(updatedUrl, events, webhookId);
    }
}
