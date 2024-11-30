package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.clients.StripePaymentGateway;
import org.example.evaluations.evaluation.dtos.SessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService implements ISessionService {

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    public SessionDto createSession(String successUrl, List<Long> amounts, List<String> productNames, List<Long> quantities) {
        return stripePaymentGateway.createSession(successUrl,amounts,productNames,quantities);
    }
}
