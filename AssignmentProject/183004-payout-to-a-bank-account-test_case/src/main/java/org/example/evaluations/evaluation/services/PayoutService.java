package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.clients.RazorpayPaymentGatewayClient;
import org.example.evaluations.evaluation.dtos.PayoutPurpose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayoutService implements IPayoutService {

    @Autowired
    private RazorpayPaymentGatewayClient razorpayPaymentGatewayClient;

    @Override
    public String createPayoutToBankAccount(String accountNumber, Double amount, PayoutPurpose purpose, String referenceId, String narration) {
        return razorpayPaymentGatewayClient.createPayoutToBankAccount(accountNumber,amount,purpose,referenceId,narration);
    }
}
