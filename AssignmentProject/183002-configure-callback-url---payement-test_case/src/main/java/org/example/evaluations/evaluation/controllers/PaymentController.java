package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.CompletePaymentDto;
import org.example.evaluations.evaluation.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/completePayment")
    public String completePayment(@RequestBody CompletePaymentDto completePaymentDto) {
        return  paymentService.completePayment(completePaymentDto.getName(),completePaymentDto.getPhoneNumber(),completePaymentDto.getEmail(), completePaymentDto.getAmount(), completePaymentDto.getDescription(),completePaymentDto.getOrderId(),completePaymentDto.getCallback());
    }
}
