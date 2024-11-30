package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Employee {

    @Id
    private UUID id;

    private String alias;

    private String fullName;

    private String phoneNumber;

    private Double costToCompany;

    private Date joiningDate;

    private Double performanceRating;

    private String department;
}
