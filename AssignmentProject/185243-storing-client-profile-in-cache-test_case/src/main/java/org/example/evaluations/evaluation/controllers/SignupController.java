package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.SignupRequestDto;
import org.example.evaluations.evaluation.exceptions.UserAlreadyExistsException;
import org.example.evaluations.evaluation.models.Client;
import org.example.evaluations.evaluation.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping
    public Client perform(@RequestBody SignupRequestDto signupRequestDto) throws UserAlreadyExistsException {
       return signupService.signup(signupRequestDto.getEmail(),signupRequestDto.getPassword(),signupRequestDto.getName(),signupRequestDto.getPhoneNumber());
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleExceptions(UserAlreadyExistsException exception) {
        return exception.getMessage();
    }
}
