package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.InitializePaymentRequestDto;
import org.example.evaluations.evaluation.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/payment")
    public String initializePayment(@RequestBody InitializePaymentRequestDto initializePaymentRequestDto) {
        return paymentService.getPaymentLink(initializePaymentRequestDto.getAmount(), initializePaymentRequestDto.getQuantity(), initializePaymentRequestDto.getCallbackUrl(), initializePaymentRequestDto.getProductName());
    }
}
