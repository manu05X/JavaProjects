package org.example.evaluations.evaluation.clients;

import org.example.evaluations.evaluation.dtos.PayoutPurpose;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Component
public class RazorpayPaymentGatewayClient {

    @Value("${razorpay.id}")
    private String razorpayKeyId;

    @Value("${razorpay.secret}")
    private String razorpayKeySecret;

    private final String path = "https://api.razorpay.com/v1/payouts";

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    public String createPayoutToBankAccount(String accountNumber, Double amount, PayoutPurpose purpose, String referenceId, String narration) {
            String idempotencyKey = UUID.randomUUID().toString();
            RestTemplate restTemplate = restTemplateBuilder.build();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("X-Payout-Idempotency", idempotencyKey);
            String auth = razorpayKeyId + ":" + razorpayKeySecret;
            byte[] encodedAuth = java.util.Base64.getEncoder().encode(auth.getBytes());
            String authHeader = "Basic " + new String(encodedAuth);
            headers.set("Authorization", authHeader);

            JSONObject payload = new JSONObject();
            payload.put("account_number", accountNumber);
            payload.put("fund_account_id", "fa_00000000000001");
            payload.put("amount", amount);
            payload.put("currency", "INR");
            payload.put("mode", "IMPS");
            payload.put("purpose", purpose.name());
            payload.put("queue_if_low_balance", true);
            payload.put("reference_id", referenceId);
            payload.put("narration", narration);

            HttpEntity<String> request = new HttpEntity<>(payload.toString(), headers);
            ResponseEntity<String> response = restTemplate.exchange(path, HttpMethod.POST, request, String.class);
            return response.getBody();
    }
}
