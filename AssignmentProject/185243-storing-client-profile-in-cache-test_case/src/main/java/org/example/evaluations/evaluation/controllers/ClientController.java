package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.exceptions.UserNotFoundException;
import org.example.evaluations.evaluation.models.Client;
import org.example.evaluations.evaluation.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/id/{id}")
    public Client getClientFromId(@PathVariable  Long id) throws UserNotFoundException {
        return clientService.getClientFromId(id);
    }


    @GetMapping("/email/{email}")
    public Client getClientFromEmail(@PathVariable String email) throws UserNotFoundException {
        return clientService.getClientFromEmail(email);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private String handleExceptions(Exception exception) {
        return exception.getMessage();
    }
}
