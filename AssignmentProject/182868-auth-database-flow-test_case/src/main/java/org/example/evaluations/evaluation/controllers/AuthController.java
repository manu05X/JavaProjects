package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.LoginRequest;
import org.example.evaluations.evaluation.dtos.LoginResponse;
import org.example.evaluations.evaluation.dtos.SignupRequest;
import org.example.evaluations.evaluation.dtos.SignupResponse;
import org.example.evaluations.evaluation.exceptions.BadCredentialsException;
import org.example.evaluations.evaluation.exceptions.UserNotSignedUpException;
import org.example.evaluations.evaluation.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/signup")
    public SignupResponse signup(@RequestBody SignupRequest signupRequest) {
       return authService.signup(signupRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws BadCredentialsException, UserNotSignedUpException {
        try {
            return authService.login(loginRequest);
        }catch (BadCredentialsException exception) {
            throw  exception;
        }catch (UserNotSignedUpException exception) {
            throw exception;
        }
    }
}


//{
//        "email" : "ak@ak",
//        "password" : "ak",
//        "firstName" : "a",
//        "lastName" : "k",
//        "address" : "akakakaka",
//        "phoneNumber" : "999999"
//}
