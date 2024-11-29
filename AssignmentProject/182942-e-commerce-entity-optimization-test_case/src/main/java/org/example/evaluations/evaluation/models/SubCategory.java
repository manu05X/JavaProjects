package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class SubCategory extends BaseModel {

    private String name;

    private String description;

    @ManyToOne
    private Category category;
}
