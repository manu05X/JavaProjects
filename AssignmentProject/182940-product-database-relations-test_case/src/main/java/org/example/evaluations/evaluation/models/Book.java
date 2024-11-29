package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;

@Entity
public class Book extends Product {
    private BookType bookType;
}
