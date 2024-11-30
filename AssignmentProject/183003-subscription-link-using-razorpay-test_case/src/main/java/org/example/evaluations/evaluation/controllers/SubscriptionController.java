package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.CreateSubscriptionRequest;
import org.example.evaluations.evaluation.services.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

    @Autowired
    private ISubscriptionService subscriptionService;

    @PostMapping("/subscription")
    public String createSubscription(@RequestBody CreateSubscriptionRequest subscriptionRequest) {
        return subscriptionService.createSubscription(subscriptionRequest);
    }
}
