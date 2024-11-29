package org.example.evaluations.evaluation.models;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Employee {
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;
}
