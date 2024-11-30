package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Inventory extends BaseModel {
    @OneToOne
    private Item item;

    private Double count;

    private Double orderingCost;

    private Double stockOutCost;
}
