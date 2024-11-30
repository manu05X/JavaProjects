package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.PayoutController;
import org.example.evaluations.evaluation.dtos.PayoutPurpose;
import org.example.evaluations.evaluation.dtos.PayoutRequestDto;
import org.example.evaluations.evaluation.services.IPayoutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PayoutController.class)
public class PayoutControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPayoutService payoutService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreatePayoutToBank() throws Exception {
        // Given
        PayoutRequestDto payoutRequestDto = new PayoutRequestDto();
        payoutRequestDto.setAccountNumber("7878780080316316");
        payoutRequestDto.setAmount(1000000.0);
        payoutRequestDto.setPurpose(PayoutPurpose.refund);
        payoutRequestDto.setReferenceId("Acme Transaction ID 12345");
        payoutRequestDto.setNarration("Acme Corp Fund Transfer");

        String expectedResponse = "{\"status\":\"success\"}";
        when(payoutService.createPayoutToBankAccount(
                payoutRequestDto.getAccountNumber(),
                payoutRequestDto.getAmount(),
                payoutRequestDto.getPurpose(),
                payoutRequestDto.getReferenceId(),
                payoutRequestDto.getNarration()
        )).thenReturn(expectedResponse);

        // When & Then
        mockMvc.perform(post("/payout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payoutRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}
