package org.example.evaluations.evaluation.services;

public interface IPaymentService {
    String getPaymentLink(Long amount, Long quantity, String callbackUrl, String productName);
}
