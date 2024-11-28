package org.example.evaluations.evaluation.dtos;

import lombok.Data;
import org.example.evaluations.evaluation.models.AmazonProduct;

import java.util.List;

@Data
public class AmazonProductDataDto {
    private List<AmazonProduct> products;
}
