package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreUserDto {
    private String email;
    private String password;
    private String username;
    private FakeStoreUserNameDto name;
    private FakeStoreUserAddressDto address;
}
