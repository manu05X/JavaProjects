package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Item extends BaseModel {
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private Boolean isPremium;

    @OneToOne(mappedBy = "item")
    private Inventory inventory;
}
