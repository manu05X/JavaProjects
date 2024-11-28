package org.example.evaluations.evaluation.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private Long id;
    private String email;
    private String password;
    private String username;
    private Name name;
    private Address address;
}
