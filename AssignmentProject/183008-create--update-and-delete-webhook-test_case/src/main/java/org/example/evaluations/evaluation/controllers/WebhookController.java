package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.WebhookDto;
import org.example.evaluations.evaluation.dtos.Webhook;
import org.example.evaluations.evaluation.services.IWebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private IWebhookService webhookService;

    @PostMapping
    public Webhook createWebhook(@RequestBody WebhookDto createWebhookDto) {
        return  webhookService.createWebhook(createWebhookDto.getUrl(), createWebhookDto.getEvents());
    }

    @DeleteMapping("/{id}")
    public Boolean deleteWebhook(@PathVariable("id") String id) {
        return  webhookService.deleteWebhook(id);
    }

    @PatchMapping("/{id}")
    public Webhook updateWebhook(@RequestBody WebhookDto updateWebhookDto, @PathVariable("id") String id) {
        return webhookService.updateWebhook(updateWebhookDto.getUrl(), updateWebhookDto.getEvents(), id);
    }
}
