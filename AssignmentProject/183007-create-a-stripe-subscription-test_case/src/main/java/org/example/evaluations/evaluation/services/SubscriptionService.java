package org.example.evaluations.evaluation.services;

import com.stripe.param.PlanCreateParams;
import org.example.evaluations.evaluation.clients.StripePaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    public String createSubscriptionForProduct(String customerName,String customerEmail,Long productAmount, String productName, PlanCreateParams.Interval interval) {
        return stripePaymentGateway.createSubscriptionForProduct(customerName, customerEmail, productAmount, productName, interval);
    }
}
