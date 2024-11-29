package org.example.evaluations.evaluation.dtos;

import lombok.Data;

@Data
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
}
