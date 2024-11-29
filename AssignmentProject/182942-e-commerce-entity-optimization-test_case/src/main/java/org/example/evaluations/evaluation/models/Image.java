package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Image extends BaseModel {
    private String resolution;

    private Long sizeInKb;

    @ManyToOne
    private Product product;

    private String descriptiveName;
}
