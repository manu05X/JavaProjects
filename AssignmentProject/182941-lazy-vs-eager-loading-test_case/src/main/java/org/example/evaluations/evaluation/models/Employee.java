package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Employee {

    @Id
    private Long id;

    private String name;

    private Double costToCompany;

    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;

    @OneToMany(mappedBy = "employee",fetch = FetchType.EAGER)
    List<IdentityProof> proofs;
}
