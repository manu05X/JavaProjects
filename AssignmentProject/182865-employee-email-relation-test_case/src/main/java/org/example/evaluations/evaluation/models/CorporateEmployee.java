package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class CorporateEmployee {
    @Id
    private Long id;

    private String name;

    private String designation;

    @OneToOne(mappedBy = "employee")
    @PrimaryKeyJoinColumn
    private CorporateEmailAddress emailAddress;
}
