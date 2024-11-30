package org.example.evaluations.clients;

import org.example.evaluations.evaluation.clients.RazorpayPaymentGatewayClient;
import org.example.evaluations.evaluation.dtos.PayoutPurpose;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RazorpayPaymentGatewayClientUnitTest {

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private RazorpayPaymentGatewayClient razorpayPaymentGatewayClient;

    @BeforeEach
    void setUp() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }


    @Test
    void testCreatePayoutToBankAccount() {
        String accountNumber = "7878780080316316";
        Double amount = 1000000.0;
        String purpose = "refund";
        String referenceId = "Acme Transaction ID 12345";
        String narration = "Acme Corp Fund Transfer";
        String expectedResponse = "{\"status\":\"success\"}";

        ResponseEntity<String> responseEntity = ResponseEntity.ok(expectedResponse);

        when(restTemplate.exchange(eq("https://api.razorpay.com/v1/payouts"), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);

        // When
        String response = razorpayPaymentGatewayClient.createPayoutToBankAccount(accountNumber, amount, PayoutPurpose.valueOf(purpose), referenceId, narration);

        // Then
        assertNotNull(response);
        assertEquals(expectedResponse, response);

        verify(restTemplate).exchange(
                eq("https://api.razorpay.com/v1/payouts"),
                eq(HttpMethod.POST),
                argThat(argument -> {
                    HttpHeaders headers1 = argument.getHeaders();
                    String autho = headers1.getFirst("Authorization");
                    String contentType = headers1.getFirst("Content-Type");
                    String idempotency = headers1.getFirst("X-Payout-Idempotency");

                    return autho != null &&
                            contentType != null &&
                            contentType.equals("application/json") &&
                            idempotency != null &&
                            autho.startsWith("Basic ") &&
                            autho.endsWith(Base64.getEncoder().encodeToString("razorpayId:razorpaySecret".getBytes())) &&
                            argument.getBody() != null &&
                            validateBody((String) argument.getBody(),accountNumber,amount,referenceId,narration);
                }),
                eq(String.class)
        );
    }

    private boolean validateBody(String body, String accountNumber, Double amount, String referenceId, String narration) {
        try {
            JSONObject json = new JSONObject(body);
            return json.getString("account_number").equals(accountNumber) &&
                    json.getDouble("amount")==(amount) &&
                    json.getString("reference_id").equals(referenceId) &&
                    json.getString("fund_account_id").equals("fa_00000000000001") &&
                    json.getString("currency").equals("INR") &&
                    json.getString("mode").equals("IMPS") &&
                    json.getBoolean("queue_if_low_balance") &&
                    json.getString("narration").equals(narration);
        } catch (Exception e) {
            return false;
        }
    }
}
