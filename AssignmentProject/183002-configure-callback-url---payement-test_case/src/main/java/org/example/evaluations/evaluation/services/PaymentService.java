package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.clients.RazorpayPaymentGatewayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private RazorpayPaymentGatewayClient razorpayPaymentGatewayClient;

    @Override
    public String completePayment(String name, String phoneNumber, String email, Double amount, String description, String orderId, URL callback) {
        return razorpayPaymentGatewayClient.completePaymentAndOpenCallBack(name, phoneNumber, email, amount, description, orderId,callback);
    }
}
