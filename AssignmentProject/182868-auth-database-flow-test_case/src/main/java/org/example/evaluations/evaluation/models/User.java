package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name="Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    @OneToOne
    private AuthCredential authCredential;
}
