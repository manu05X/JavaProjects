package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity(name="orders")
public class Order extends BaseModel {
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<ItemDetail> items;

    private Double totalCost;

    @OneToMany(mappedBy = "order")
    private List<OrderStateTimeMapping> orderTimeline;
}
