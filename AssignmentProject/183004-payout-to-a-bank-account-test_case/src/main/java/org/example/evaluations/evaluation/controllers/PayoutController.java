package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.PayoutRequestDto;
import org.example.evaluations.evaluation.services.IPayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayoutController {

    @Autowired
    private IPayoutService payoutService;

    @PostMapping("/payout")
    public String createPayoutToBank(@RequestBody PayoutRequestDto payoutRequestDto) {
        return payoutService.createPayoutToBankAccount(payoutRequestDto.getAccountNumber(),payoutRequestDto.getAmount(),payoutRequestDto.getPurpose(),payoutRequestDto.getReferenceId(),payoutRequestDto.getNarration());
    }
}
