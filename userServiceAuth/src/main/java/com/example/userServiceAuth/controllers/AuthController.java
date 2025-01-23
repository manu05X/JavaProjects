package com.example.userServiceAuth.controllers;

import com.example.userServiceAuth.dtos.*;
import com.example.userServiceAuth.models.SessionStatus;
import com.example.userServiceAuth.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    // Creating a new User
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto requestDto){
        UserDto userDto = authService.signUp(requestDto.getEmail(), requestDto.getPassword());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto request){
        return authService.login(request.getEmail(), request.getPassword());
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request){
        authService.logout(request.getToken(), request.getUserId());

        return ResponseEntity.ok().build();
    }



    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDto requestDto){
        SessionStatus sessionStatus = authService.validate(requestDto.getToken(), requestDto.getUserId());

        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }


}
