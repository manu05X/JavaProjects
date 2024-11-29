package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

@Entity
public class Subscription {
    @Id
    protected Long id;

    protected double charges;

    @Enumerated(value = EnumType.STRING)
    protected Status status;
}
