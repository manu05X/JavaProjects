package com.example.userServiceAuth.controllers;

import com.example.userServiceAuth.dtos.SetUserRolesRequestDto;
import com.example.userServiceAuth.dtos.UserDto;
import com.example.userServiceAuth.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") Long userId){
        UserDto userDto = userService.getUserDetails(userId);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDto> setUserRoles(@PathVariable("id") Long userId, @RequestBody SetUserRolesRequestDto requestDto){
        UserDto userDto = userService.setUserRoles(userId, requestDto.getRoleIds());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
