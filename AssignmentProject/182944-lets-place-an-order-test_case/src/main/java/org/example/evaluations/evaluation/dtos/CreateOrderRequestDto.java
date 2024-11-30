package org.example.evaluations.evaluation.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class CreateOrderRequestDto {
    private Map<Long,Long> itemQuantityMap;
    private Long customerId;
}
