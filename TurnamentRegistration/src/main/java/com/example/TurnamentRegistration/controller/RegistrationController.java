package com.example.TurnamentRegistration.controller;

import com.example.TurnamentRegistration.entity.Registration;
import com.example.TurnamentRegistration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    RegistrationService service;

    @GetMapping("/registrations")
    public List<Registration> getAllRegistration(){
        return service.getAllRegistrations();
    }

    @GetMapping("/registrations/{id}")
    public Registration getRegistration(@PathVariable int id){
        return service.getRegistration(id);
    }

    @PostMapping("registrations")
    public Registration addRegistration(@RequestBody Registration registration){
        return service.addRegistration(registration);
    }

    @DeleteMapping("/registrations/{id}")
    public void deleteRegistration(@PathVariable int id){
        service.deleteRegistration(id);
    }
}
