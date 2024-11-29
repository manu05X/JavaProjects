package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="AuthCredentials")
@Data
public class AuthCredential {
    @Id
    private String email;

    private String password;
}
