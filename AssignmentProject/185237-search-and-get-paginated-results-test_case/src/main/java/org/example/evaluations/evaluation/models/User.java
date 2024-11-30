package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name="user_")
public class User {

    @Id
    private Long id;

    private String emailId;

    private String name;

    private String phoneNumber;

    private String address;

    private Sex sex;

    private Integer age;
}
