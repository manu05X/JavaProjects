package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.LoginRequestDto;
import org.example.evaluations.evaluation.dtos.ValidateTokenRequestDto;
import org.example.evaluations.evaluation.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequestDto loginRequestDto) {
        return authenticationService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
    }

    @PostMapping("/validateToken")
   public Boolean validateToken(@RequestBody ValidateTokenRequestDto validateTokenRequestDto) {
        return authenticationService.validateToken(validateTokenRequestDto.getEmail(),validateTokenRequestDto.getToken());
    }
}
