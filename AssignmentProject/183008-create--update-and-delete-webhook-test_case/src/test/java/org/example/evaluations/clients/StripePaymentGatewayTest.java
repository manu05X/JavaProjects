package org.example.evaluations.clients;

import org.example.evaluations.evaluation.clients.StripePaymentGateway;
import org.example.evaluations.evaluation.dtos.Webhook;
import org.example.evaluations.evaluation.dtos.WebhookStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class StripePaymentGatewayTest {

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    private String URL = "https://29e2-223-187-103-60.ngrok-free.app/stripeWebhook";

    @Value("${fromTest}")
    private String apiKey;

    @Test
    void testCreate_Update_DeleteWebhook() {
        stripePaymentGateway.apiKey = apiKey;

        //create flow
        List<String> events = new ArrayList<>();
        events.add("PAYMENT_LINK__CREATED");

        Webhook createdWebhook = stripePaymentGateway.createWebhook(this.URL,events);

        assertNotNull(createdWebhook);
        assertNotNull(createdWebhook.getId());
        assertNotNull(createdWebhook.getSecret());
        assertTrue(createdWebhook.getEvents().contains("payment_link.created"));
        assertTrue(createdWebhook.getEvents().size()==1);
        assertTrue(createdWebhook.getStatus()== WebhookStatus.enabled);
        assertEquals(createdWebhook.getUrl(),this.URL);

        //update flow
        events.add("PAYMENT_LINK__UPDATED");

        Webhook updatedWebhook = stripePaymentGateway.updateWebhook(this.URL,events, createdWebhook.getId());

        assertNotNull(updatedWebhook);
        assertNotNull(updatedWebhook.getId());
        assertTrue(updatedWebhook.getEvents().contains("payment_link.created"));
        assertTrue(updatedWebhook.getEvents().contains("payment_link.updated"));
        assertTrue(updatedWebhook.getEvents().size()==2);
        assertTrue(updatedWebhook.getStatus()== WebhookStatus.enabled);
        assertEquals(updatedWebhook.getUrl(),this.URL);

        //delete flow
        Boolean isDeleted = stripePaymentGateway.deleteWebhook(updatedWebhook.getId());
        assertTrue(isDeleted);

    }
}
