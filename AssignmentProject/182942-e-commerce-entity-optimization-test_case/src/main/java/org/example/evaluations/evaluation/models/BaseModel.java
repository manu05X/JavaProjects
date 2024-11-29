package org.example.evaluations.evaluation.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseModel {
    @Id
    private Long id;

    private State state;
}
