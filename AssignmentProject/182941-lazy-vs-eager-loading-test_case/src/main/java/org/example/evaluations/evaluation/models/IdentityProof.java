package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class IdentityProof {
    @Id
    private Long id;

    private String name;

    @ManyToOne
    private Employee employee;
}
