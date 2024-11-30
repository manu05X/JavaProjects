package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefundRequestDto {
    Double amount;
    String receipt;
    RefundSpeed refundSpeed;
}
