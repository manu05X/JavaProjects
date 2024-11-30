package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.Webhook;

import java.util.List;


public interface IWebhookService {
    Webhook createWebhook(String url, List<String> events);
    Boolean deleteWebhook(String webhookId);
    Webhook updateWebhook(String updatedUrl, List<String> events, String webhookId);
}
