package org.example.evaluations.evaluation.clients;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.WebhookEndpoint;
import com.stripe.param.*;
import org.example.evaluations.evaluation.dtos.Webhook;
import org.example.evaluations.evaluation.dtos.WebhookStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StripePaymentGateway {

    @Value("${stripe.key}")
    public String apiKey;

    public Webhook createWebhook(String url, List<String> events) {
        try {
            Stripe.apiKey = this.apiKey;

            List<WebhookEndpointCreateParams.EnabledEvent> enabledEvents = new ArrayList<>();

            for(String event : events) {
                enabledEvents.add(WebhookEndpointCreateParams.EnabledEvent.valueOf(event));
            }

            WebhookEndpointCreateParams params =
                    WebhookEndpointCreateParams.builder()
                            .addAllEnabledEvent(enabledEvents)
                            .setUrl(url)
                            .build();

            WebhookEndpoint webhookEndpoint = WebhookEndpoint.create(params);

            Webhook webhook = new Webhook();
            webhook.setId(webhookEndpoint.getId());
            webhook.setUrl(webhookEndpoint.getUrl());
            webhook.setEvents(webhookEndpoint.getEnabledEvents());
            webhook.setSecret(webhookEndpoint.getSecret());
            webhook.setStatus(WebhookStatus.valueOf(webhookEndpoint.getStatus()));
            return webhook;

        }catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public Boolean deleteWebhook(String webhookId) {
        try {
            Stripe.apiKey = this.apiKey;
            WebhookEndpoint resource = WebhookEndpoint.retrieve(webhookId);
            WebhookEndpoint webhookEndpoint = resource.delete();
            return webhookEndpoint.getDeleted();
        } catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }


    public Webhook updateWebhook(String updatedUrl, List<String> events, String webhookId) {
        try {
            Stripe.apiKey = this.apiKey;

            List<WebhookEndpointUpdateParams.EnabledEvent> updatedEvents = new ArrayList<>();

            if(events != null) {
                for (String event : events) {
                    updatedEvents.add(WebhookEndpointUpdateParams.EnabledEvent.valueOf(event));
                }
            }

            WebhookEndpoint resource = WebhookEndpoint.retrieve(webhookId);

            WebhookEndpointUpdateParams params = null;

            if(updatedUrl != null && !updatedEvents.isEmpty()) {
                 params =
                        WebhookEndpointUpdateParams.builder()
                                .addAllEnabledEvent(updatedEvents)
                                .setUrl(updatedUrl)
                                .build();
            } else if(!updatedEvents.isEmpty()) {
                 params =
                        WebhookEndpointUpdateParams.builder()
                                .addAllEnabledEvent(updatedEvents)
                                .build();
            } else {
                 params =
                        WebhookEndpointUpdateParams.builder()
                                .setUrl(updatedUrl)
                                .build();
            }

            WebhookEndpoint webhookEndpoint = resource.update(params);

            Webhook webhook = new Webhook();
            webhook.setId(webhookEndpoint.getId());
            webhook.setUrl(webhookEndpoint.getUrl());
            webhook.setEvents(webhookEndpoint.getEnabledEvents());
            webhook.setSecret(webhookEndpoint.getSecret());
            webhook.setStatus(WebhookStatus.valueOf(webhookEndpoint.getStatus()));
            return webhook;

        }catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

}
