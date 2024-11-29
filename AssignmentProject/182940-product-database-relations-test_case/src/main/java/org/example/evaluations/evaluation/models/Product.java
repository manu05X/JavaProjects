package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {
    @Id
    protected Long id;

    protected String name;
    @Enumerated(value = EnumType.STRING)
    protected Status status;

    @OneToOne
    private Subscription subscription;
}
