package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDto {
    private String title;
    private String description;
    private String category;
    private String imageUrl;
    private Double price;
}
