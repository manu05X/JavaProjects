package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.PaymentController;
import org.example.evaluations.evaluation.dtos.InitializePaymentRequestDto;
import org.example.evaluations.evaluation.services.IPaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(PaymentController.class)
public class PaymentControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IPaymentService paymentService;

    @Test
    void testInitializePaymentSuccess() throws Exception {
        // Arrange
        InitializePaymentRequestDto requestDto = new InitializePaymentRequestDto();
        requestDto.setAmount(1000L);
        requestDto.setQuantity(1L);
        requestDto.setCallbackUrl("http://example.com/callback");
        requestDto.setProductName("Test Product");

        String expectedPaymentLink = "http://example.com/payment";

        when(paymentService.getPaymentLink(requestDto.getAmount(), requestDto.getQuantity(), requestDto.getCallbackUrl(), requestDto.getProductName()))
                .thenReturn(expectedPaymentLink);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/payment")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedPaymentLink))
                .andDo(print());
    }
}
