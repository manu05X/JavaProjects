package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product {

    @Id
    private Long id;

    private Double weight;

    private Double amount;

    private Long age;

    private String category;

    private String name;
}
