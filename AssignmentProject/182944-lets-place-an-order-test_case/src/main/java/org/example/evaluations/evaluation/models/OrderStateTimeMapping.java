package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Setter
@Getter
public class OrderStateTimeMapping extends BaseModel {

    private OrderState orderState = OrderState.CONFIRMED;

    private Date date = new Date();

    @ManyToOne
    private Order order;
}
