package org.example.evaluations.evaluation.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class LoginResponse {
    private String userEmail;
    private Date tokenValidity;
    private String token;
}
