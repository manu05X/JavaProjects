package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.PaymentController;
import org.example.evaluations.evaluation.dtos.CompletePaymentDto;
import org.example.evaluations.evaluation.services.IPaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URL;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
public class PaymentControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPaymentService paymentService;

    @Test
    void completePayment_ShouldReturnSuccessMessage() throws Exception {
        // Given
        CompletePaymentDto dto = new CompletePaymentDto();
        dto.setName("John Doe");
        dto.setPhoneNumber("1234567890");
        dto.setEmail("john.doe@example.com");
        dto.setAmount(100.00);
        dto.setDescription("Payment for order");
        dto.setOrderId("ORD12345");
        dto.setCallback(new URL("http://example.com/callback"));

        String expectedResponse = "Payment completed successfully";

        when(paymentService.completePayment(
                dto.getName(),
                dto.getPhoneNumber(),
                dto.getEmail(),
                dto.getAmount(),
                dto.getDescription(),
                dto.getOrderId(),
                dto.getCallback()
        )).thenReturn(expectedResponse);

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/completePayment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"phoneNumber\":\"1234567890\",\"email\":\"john.doe@example.com\",\"amount\":100.00,\"description\":\"Payment for order\",\"orderId\":\"ORD12345\",\"callback\":\"http://example.com/callback\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse))
                .andDo(MockMvcResultHandlers.print());
    }
}
