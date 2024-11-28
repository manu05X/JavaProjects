package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.evaluations.evaluation.models.Address;
import org.example.evaluations.evaluation.models.Name;

@Setter
@Getter
public class UserRequestDto {
    private String email;
    private String password;
    private String username;
    private Name name;
    private Address address;
}
