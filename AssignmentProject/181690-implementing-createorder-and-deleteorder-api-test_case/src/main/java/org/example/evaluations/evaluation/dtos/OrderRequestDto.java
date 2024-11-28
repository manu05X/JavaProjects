package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestDto {
    private Long customerId;
    private Double totalAmount;
}
