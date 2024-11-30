package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubscriptionRequest {
    Long chargeCount;
    Long totalNumberOfBillingCycles;
    Long starting;
    Long ending;
    String phone;
    String email;
    Period planPeriod;
    String planTitle;
    String planDescription;
    Double planAmount;
}
