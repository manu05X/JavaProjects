package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;

@Entity
public class Pen extends Product {
    private PenType penType;
}
