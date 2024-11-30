package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.clients.RazorpayPaymentGatewayClient;
import org.example.evaluations.evaluation.dtos.CreateSubscriptionRequest;
import org.example.evaluations.evaluation.utils.SubscriptionRequestConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    private RazorpayPaymentGatewayClient razorpayPaymentGatewayClient;

    @Autowired
    private SubscriptionRequestConverter requestConverter;

    @Override
    public String createSubscription(CreateSubscriptionRequest subscriptionRequest) {
        return razorpayPaymentGatewayClient.createSubscriptionLink(requestConverter.from(subscriptionRequest)).get("short_url").toString();
    }
}
