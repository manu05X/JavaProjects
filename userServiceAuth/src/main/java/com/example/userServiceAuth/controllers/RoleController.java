package com.example.userServiceAuth.controllers;

import com.example.userServiceAuth.dtos.CreateRoleRequestDto;
import com.example.userServiceAuth.models.Role;
import com.example.userServiceAuth.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(CreateRoleRequestDto requestDto){
        Role role = roleService.createRole(requestDto.getName());

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

}
