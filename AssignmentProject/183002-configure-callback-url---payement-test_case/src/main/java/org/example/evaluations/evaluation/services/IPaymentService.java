package org.example.evaluations.evaluation.services;

import java.net.URL;

public interface IPaymentService {
    String completePayment(String name, String phoneNumber, String email, Double amount, String description, String orderId, URL callback);
}
