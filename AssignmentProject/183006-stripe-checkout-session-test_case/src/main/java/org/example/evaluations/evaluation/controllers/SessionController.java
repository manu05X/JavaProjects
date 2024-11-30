package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.CreateSessionDto;
import org.example.evaluations.evaluation.dtos.SessionDto;
import org.example.evaluations.evaluation.services.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @Autowired
    private ISessionService sessionService;

    @PostMapping("/session")
    public SessionDto createSession(@RequestBody CreateSessionDto createSessionDto) {
        return  sessionService.createSession(createSessionDto.getSuccessUrl(),createSessionDto.getAmounts(),createSessionDto.getProductNames(),createSessionDto.getQuantities());
    }
}
