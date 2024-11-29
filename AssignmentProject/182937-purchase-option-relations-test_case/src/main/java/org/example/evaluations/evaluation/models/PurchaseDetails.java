package org.example.evaluations.evaluation.models;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PurchaseDetails {
    protected String owner;

}
