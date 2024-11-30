package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Setter
@Getter
public class CompletePaymentDto {
    String name;
    String phoneNumber;
    String email;
    Double amount;
    String description;
    String orderId;
    URL callback;
}
