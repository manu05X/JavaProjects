package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private String description;

    private String imageUrl;

    private Double price;

    private Date createdAt;
}
