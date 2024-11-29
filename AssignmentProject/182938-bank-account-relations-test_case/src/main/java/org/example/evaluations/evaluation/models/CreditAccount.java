package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "CreditAccount")
@PrimaryKeyJoinColumn(name = "account_id")
public class CreditAccount extends Account {

    @OneToMany(mappedBy = "credit")
    private Set<CreditCard> creditCard = new HashSet<>();

    private double interestRate;
}

