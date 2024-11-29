package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "DebitCard")
public class DebitCard {

    @Id
    private String id;

    @OneToOne
    private DebitAccount debit;
}
