package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

@Entity
public class PayLater extends PurchaseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
}
