package org.example.evaluations.evaluation.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("Book")
public class Book extends Publication {
    private Integer numberOfPages;

    private Double cost;
}
