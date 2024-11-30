package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.SessionDto;

import java.util.List;

public interface ISessionService {
    SessionDto createSession(String successUrl, List<Long> amounts, List<String> productNames, List<Long> quantities);
}
