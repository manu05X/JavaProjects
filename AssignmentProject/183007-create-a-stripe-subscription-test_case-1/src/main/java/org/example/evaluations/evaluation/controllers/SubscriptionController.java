package org.example.evaluations.evaluation.controllers;

import com.stripe.param.PlanCreateParams;
import org.example.evaluations.evaluation.dtos.SubscriptionRequestDto;
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
    public String createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        PlanCreateParams.Interval interval =
                PlanCreateParams.Interval.valueOf(subscriptionRequestDto.getBillingFrequency().name());

        return subscriptionService
          .createSubscriptionForProduct(subscriptionRequestDto.getCustomerName(),subscriptionRequestDto.getCustomerEmail(),subscriptionRequestDto.getProductAmount(),subscriptionRequestDto.getProductName(),interval);
    }
}
