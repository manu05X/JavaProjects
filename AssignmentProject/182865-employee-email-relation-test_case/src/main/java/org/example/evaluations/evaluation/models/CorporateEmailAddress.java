package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

import java.util.Date;

@Entity
public class CorporateEmailAddress {
    @Id
    private Long id;

    private String tenant;

    private Date createdAt;

    @OneToOne
    @MapsId
    private CorporateEmployee employee;
}
