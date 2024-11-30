package org.example.evaluations.evaluation.clients;

import com.razorpay.Plan;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Subscription;
import org.example.evaluations.evaluation.dtos.RazorpayPlanRequest;
import org.example.evaluations.evaluation.dtos.RazorpaySubscriptionRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class RazorpayPaymentGatewayClient {

    @Autowired
    private RazorpayClient razorpayClient;

    private final String offerId = "offer_JTUADI4ZWBGWur";  // Use this offerId wherever required

    public Subscription createSubscriptionLink(RazorpaySubscriptionRequest subscriptionInput) {
        try {
            Plan plan = createPlan(subscriptionInput.getRazorpayPlanRequest());
            JSONObject subscriptionRequest = new JSONObject();
            subscriptionRequest.put("plan_id", plan.get("id").toString());
            subscriptionRequest.put("total_count", subscriptionInput.getTotalCount());
            subscriptionRequest.put("quantity", subscriptionInput.getQuantity());
            subscriptionRequest.put("customer_notify", 1);
            subscriptionRequest.put("start_at", subscriptionInput.getStartTime());
            subscriptionRequest.put("expire_by", subscriptionInput.getExpiryTime());
            List<Object> addons = new ArrayList<>();
            JSONObject linesItem = new JSONObject();
            JSONObject item = new JSONObject();
            item.put("name", "Delivery charges");
            item.put("amount", 30000);
            item.put("currency", "INR");
            linesItem.put("item", item);
            addons.add(linesItem);
            subscriptionRequest.put("addons", addons);
            subscriptionRequest.put("offer_id", offerId);
            JSONObject notes = new JSONObject();
            notes.put("notes_key_1", "Tea, Earl Grey, Hot");
            notes.put("notes_key_2", "Tea, Earl Grey… decaf.");
            subscriptionRequest.put("notes", notes);
            JSONObject notifyInfo = new JSONObject();
            notifyInfo.put("notify_phone", subscriptionInput.getRazorpayCustomerContactDetails().getPhoneNumber());
            notifyInfo.put("notify_email", subscriptionInput.getRazorpayCustomerContactDetails().getEmail());
            subscriptionRequest.put("notify_info", notifyInfo);
            Subscription subscription = razorpayClient.subscriptions.create(subscriptionRequest);
            return subscription;
        } catch (RazorpayException exception) {
             throw new RuntimeException(exception.getMessage());
        }
    }

    private Plan createPlan(RazorpayPlanRequest planInput) {
        try {
            JSONObject planRequest = new JSONObject();
            planRequest.put("period",planInput.getFrequency());
            planRequest.put("interval",1);
            JSONObject item = new JSONObject();
            item.put("name",planInput.getName());
            item.put("amount",planInput.getAmount());
            item.put("currency","INR");
            item.put("description",planInput.getDescription());
            planRequest.put("item",item);
            JSONObject notes = new JSONObject();
            notes.put("notes_key_1","Tea, Earl Grey, Hot");
            notes.put("notes_key_2","Tea, Earl Grey… decaf.");
            planRequest.put("notes",notes);
            Plan plan = razorpayClient.plans.create(planRequest);
            return plan;
        }catch (RazorpayException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
