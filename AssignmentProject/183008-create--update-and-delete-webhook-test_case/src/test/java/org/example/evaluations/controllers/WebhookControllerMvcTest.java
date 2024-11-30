package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.WebhookController;
import org.example.evaluations.evaluation.dtos.Webhook;
import org.example.evaluations.evaluation.dtos.WebhookDto;
import org.example.evaluations.evaluation.dtos.WebhookStatus;
import org.example.evaluations.evaluation.services.IWebhookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebhookController.class)
public class WebhookControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IWebhookService webhookService;

    @Test
    public void testCreateWebhook() throws Exception {
        WebhookDto webhookDto = new WebhookDto();
        webhookDto.setUrl("https://example.com/webhook");
        webhookDto.setEvents(Collections.singletonList("event"));

        Webhook expectedWebhook = new Webhook();
        expectedWebhook.setId("webhook-id");
        expectedWebhook.setSecret("secret");
        expectedWebhook.setEvents(webhookDto.getEvents());
        expectedWebhook.setUrl(webhookDto.getUrl());
        expectedWebhook.setStatus(WebhookStatus.enabled);

        when(webhookService.createWebhook(anyString(), anyList())).thenReturn(expectedWebhook);

        mockMvc.perform(MockMvcRequestBuilders.post("/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(webhookDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedWebhook)));
    }

    @Test
    public void testDeleteWebhook() throws Exception {
        String webhookId = "webhook-id";
        when(webhookService.deleteWebhook(webhookId)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/webhook/{id}", webhookId))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testUpdateWebhook() throws Exception {
        WebhookDto webhookDto = new WebhookDto();
        webhookDto.setUrl("https://example.com/updated-webhook");
        webhookDto.setEvents(Collections.singletonList("event"));

        String webhookId = "webhook-id";

        Webhook expectedWebhook = new Webhook();
        expectedWebhook.setId(webhookId);
        expectedWebhook.setSecret("new-secret");
        expectedWebhook.setEvents(webhookDto.getEvents());
        expectedWebhook.setUrl(webhookDto.getUrl());
        expectedWebhook.setStatus(WebhookStatus.disabled);

        when(webhookService.updateWebhook(anyString(), anyList(), anyString())).thenReturn(expectedWebhook);

        mockMvc.perform(MockMvcRequestBuilders.patch("/webhook/{id}", webhookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(webhookDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedWebhook)));
    }


}
