package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.clients.StripePaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    public String getPaymentLink(Long amount, Long quantity, String callbackUrl, String productName) {
        return stripePaymentGateway.getPaymentLink(amount,quantity,callbackUrl,productName);
    }
}
