package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "DebitAccount")
@PrimaryKeyJoinColumn(name = "account_id")
public class DebitAccount extends Account {

    @OneToOne(mappedBy = "debit")
    private DebitCard debitCard;

    private double balance;
}
