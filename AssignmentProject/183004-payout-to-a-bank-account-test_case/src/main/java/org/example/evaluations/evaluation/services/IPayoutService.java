package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.PayoutPurpose;

public interface IPayoutService {
    String createPayoutToBankAccount(String accountNumber, Double amount, PayoutPurpose purpose, String referenceId, String narration);
}
