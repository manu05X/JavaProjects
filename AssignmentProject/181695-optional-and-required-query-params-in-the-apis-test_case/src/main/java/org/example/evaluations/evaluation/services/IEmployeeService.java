package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Permission;

import java.util.List;

public interface IEmployeeService {
    List<Permission> getPermissionsBasedOnRoles(List<String> roleList);

    Boolean isOnboarded(Long id,String email);

    Boolean isIdentityProvided(String email, String password, String name);
}
