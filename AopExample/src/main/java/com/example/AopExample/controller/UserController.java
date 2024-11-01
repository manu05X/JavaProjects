package com.example.AopExample.controller;

import com.example.AopExample.entity.User;
import com.example.AopExample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> getAllUser(){
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){

        return service.getUsers(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return service.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }

}
