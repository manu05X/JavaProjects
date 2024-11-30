package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubscriptionRequestDto {
    String customerName;
    String customerEmail;
    Long productAmount;
    String productName;
    BillingFrequency billingFrequency;
}
