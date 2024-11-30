package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.CreateSubscriptionRequest;

public interface ISubscriptionService {
    String createSubscription(CreateSubscriptionRequest subscriptionRequest);
}
