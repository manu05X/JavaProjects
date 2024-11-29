package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "CreditCard")
public class CreditCard {

    @Id
    private String id;

    @ManyToOne
    private CreditAccount credit;
}
