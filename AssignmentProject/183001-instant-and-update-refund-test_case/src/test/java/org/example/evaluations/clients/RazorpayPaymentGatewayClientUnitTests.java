package org.example.evaluations.clients;

import com.razorpay.*;
import org.example.evaluations.IssueRefundObjectMatcher;
import org.example.evaluations.evaluation.clients.RazorpayPaymentGatewayClient;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RazorpayPaymentGatewayClientUnitTests {

    @Autowired
    RazorpayPaymentGatewayClient razorpayPaymentGatewayClient;

    @MockBean
    PaymentClient paymentClient;

    @MockBean
    RefundClient refundClient;

    @MockBean
    RazorpayClient razorpayClient;

    @BeforeEach
    void setUp() {
        razorpayClient.payments =paymentClient;
        razorpayClient.refunds= refundClient;
    }

    @Test
    void testIssueInstantRefund_Success() throws RazorpayException {
        // Arrange
        String expectedRefundId = "refund_12345";
        Refund mockRefund = mock(Refund.class);
        when(mockRefund.get("id")).thenReturn(expectedRefundId);
        when(razorpayClient.payments.refund(eq("pay_39QqoUAi66xm2f"), argThat(new IssueRefundObjectMatcher("receipt_123","optimum",100.0))))
                .thenReturn(mockRefund);

        // Act
        String actualRefundId = razorpayPaymentGatewayClient.issueInstantRefund(100.0, "receipt_123");

        // Assert
        assertEquals(expectedRefundId, actualRefundId);
    }

    @Test
    void testIssueInstantRefund_throwsException() throws RazorpayException {
        // Arrange
        when(razorpayClient.payments.refund(eq("pay_39QqoUAi66xm2f"), argThat(new IssueRefundObjectMatcher("receipt_123","optimum",100.0))))
                .thenThrow(new RazorpayException("Refund failed"));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                razorpayPaymentGatewayClient.issueInstantRefund(100.0, "receipt_123")
        );
        assertEquals("Refund failed", thrown.getMessage());
    }

    @Test
    void testUpdateRefund_Success() throws RazorpayException {
        // Arrange
        String expectedRefundId = "refund_67890";
        Refund mockRefund = mock(Refund.class);
        when(mockRefund.get("id")).thenReturn(expectedRefundId);
        when(razorpayClient.refunds.edit(eq("refund_67890"), any(JSONObject.class)))
                .thenReturn(mockRefund);

        // Act
        JSONObject updateInfo = new JSONObject();
        updateInfo.put("receipt", "new_receipt");
        String actualRefundId = razorpayPaymentGatewayClient.updateRefund("refund_67890", updateInfo);

        // Assert
        assertEquals(expectedRefundId, actualRefundId);
    }

    @Test
    void testUpdateRefund_throwsException() throws RazorpayException {
        // Arrange
        when(razorpayClient.refunds.edit(eq("refund_123"), any(JSONObject.class)))
                .thenThrow(new RazorpayException("Update failed"));

        // Act & Assert
        JSONObject updateInfo = new JSONObject();
        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                razorpayPaymentGatewayClient.updateRefund("refund_123", updateInfo)
        );
        assertEquals("Update failed", thrown.getMessage());
    }






}
