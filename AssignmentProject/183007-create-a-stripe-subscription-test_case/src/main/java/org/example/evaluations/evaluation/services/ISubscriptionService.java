package org.example.evaluations.evaluation.services;

import com.stripe.param.PlanCreateParams;


public interface ISubscriptionService {
    String createSubscriptionForProduct(String customerName,String customerEmail,Long productAmount, String productName, PlanCreateParams.Interval interval);
}
