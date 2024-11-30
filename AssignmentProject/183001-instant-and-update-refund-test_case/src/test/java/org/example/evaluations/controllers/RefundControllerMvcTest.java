package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.UpdateRefundObjectMatcher;
import org.example.evaluations.evaluation.controllers.RefundController;
import org.example.evaluations.evaluation.dtos.RefundRequestDto;
import org.example.evaluations.evaluation.dtos.RefundSpeed;
import org.example.evaluations.evaluation.services.IRefundService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RefundController.class)
public class RefundControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IRefundService refundService;

    @Test
    void testIssueRefund() throws Exception {
        // Arrange
        Double amount = 100.0;
        String receipt = "receipt_123";
        String expectedResponse = "refund_123";

        RefundRequestDto refundRequestDto = new RefundRequestDto();
        refundRequestDto.setAmount(amount);
        refundRequestDto.setReceipt(receipt);

        when(refundService.issueRefund(eq(amount), eq(receipt))).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(post("/issueRefund")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(refundRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void testUpdateRefund_WithNullAmount() throws Exception {
        // Arrange
        String refundId = "refund_123";
        String receipt = "receipt_123";
        String speed = "optimum";
        String expectedResponse = "refund_123";

        RefundRequestDto refundRequestDto = new RefundRequestDto();
        refundRequestDto.setAmount(null);
        refundRequestDto.setReceipt(receipt);
        refundRequestDto.setRefundSpeed(RefundSpeed.optimum);

        when(refundService.updateRefund(eq(refundId), argThat(new UpdateRefundObjectMatcher(receipt,speed,null)))).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(patch("/updateRefund/{refundId}", refundId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(refundRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void testUpdateRefund_WithNullReceipt() throws Exception {
        // Arrange
        String refundId = "refund_123";
        Double amount = 1000.0;
        String speed = "optimum";
        String expectedResponse = "refund_123";

        RefundRequestDto refundRequestDto = new RefundRequestDto();
        refundRequestDto.setAmount(amount);
        refundRequestDto.setReceipt(null);
        refundRequestDto.setRefundSpeed(RefundSpeed.optimum);

        when(refundService.updateRefund(eq(refundId), argThat(new UpdateRefundObjectMatcher(null,speed,amount)))).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(patch("/updateRefund/{refundId}", refundId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(refundRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void testUpdateRefund_WithNullSpeed() throws Exception {
        // Arrange
        String refundId = "refund_123";
        Double amount = 1000.0;
        String receipt = "receipt_12121";
        String expectedResponse = "refund_123";

        RefundRequestDto refundRequestDto = new RefundRequestDto();
        refundRequestDto.setAmount(amount);
        refundRequestDto.setReceipt(receipt);
        refundRequestDto.setRefundSpeed(null);

        when(refundService.updateRefund(eq(refundId), argThat(new UpdateRefundObjectMatcher(receipt,null,amount)))).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(patch("/updateRefund/{refundId}", refundId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(refundRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
