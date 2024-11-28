package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.FakeStoreLoginRequestDto;
import org.example.evaluations.evaluation.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody FakeStoreLoginRequestDto fakeStoreLoginRequestDto) {
        MultiValueMap<String, String> headers = authService.login(fakeStoreLoginRequestDto);
        if(headers!=null)
            return new ResponseEntity<>("login successful",headers, HttpStatus.OK);
        else
            return new ResponseEntity<>("login failed",headers,HttpStatus.UNAUTHORIZED);
    }
}
