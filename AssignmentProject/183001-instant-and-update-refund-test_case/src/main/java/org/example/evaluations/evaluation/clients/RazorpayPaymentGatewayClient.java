package org.example.evaluations.evaluation.clients;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RazorpayPaymentGatewayClient {

    @Autowired
    private RazorpayClient razorpayClient;

    private final String paymentId = "pay_39QqoUAi66xm2f"; //use this paymentId, wherever needed

    public String issueInstantRefund(Double amount, String receipt) {
        try {
            JSONObject refundRequest = new JSONObject();
            refundRequest.put("amount", amount);
            refundRequest.put("speed", "optimum");
            refundRequest.put("receipt", receipt);
            JSONObject notes = new JSONObject();
            notes.put("notes_key_1", "Tea, Earl Grey, Hot");
            notes.put("notes_key_2", "Tea, Earl Grey… decaf.");
            refundRequest.put("notes", notes);
            Refund refund = razorpayClient.payments.refund(paymentId, refundRequest);
            return refund.get("id").toString();
        }catch (RazorpayException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public String updateRefund(String refundId,JSONObject jsonObject) {
        try {
            JSONObject refundRequest = new JSONObject();
            JSONObject notes = new JSONObject();
            notes.put("notes_key_1","Tea, Earl Grey, Hot");
            notes.put("notes_key_2","Tea, Earl Grey… decaf.");
            refundRequest.put("notes",notes);
            refundRequest.put("updated_info",jsonObject);
            Refund refund = razorpayClient.refunds.edit(refundId,refundRequest);
            return refund.get("id").toString();
        }catch (RazorpayException exception) {
           throw new RuntimeException(exception.getMessage());
        }
    }
}
