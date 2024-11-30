package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ItemDetail extends BaseModel {

    @OneToOne
    private Item item;

    private Long quantity;

    @ManyToOne
    private Order order;
}
