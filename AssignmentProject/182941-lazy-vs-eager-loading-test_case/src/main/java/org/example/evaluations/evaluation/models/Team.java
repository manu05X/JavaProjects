package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Team {
    @Id
    private UUID id;

    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY)
    private List<Employee> employees;

    private Double budget;

    private String location;
}
