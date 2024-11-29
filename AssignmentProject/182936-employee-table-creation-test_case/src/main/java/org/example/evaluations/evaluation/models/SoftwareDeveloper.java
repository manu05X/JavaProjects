package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;

@Entity
public class SoftwareDeveloper extends PermanentEmployee {
    private Long leavesTaken;
}
