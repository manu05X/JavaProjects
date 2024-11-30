package org.example.evaluations.evaluation.utils;

import org.example.evaluations.evaluation.dtos.CreateSubscriptionRequest;
import org.example.evaluations.evaluation.dtos.RazorpayCustomerContactDetails;
import org.example.evaluations.evaluation.dtos.RazorpayPlanRequest;
import org.example.evaluations.evaluation.dtos.RazorpaySubscriptionRequest;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionRequestConverter {
    public RazorpaySubscriptionRequest from(CreateSubscriptionRequest createSubscriptionRequest) {
        RazorpaySubscriptionRequest razorpaySubscriptionRequest = new RazorpaySubscriptionRequest();
        razorpaySubscriptionRequest.setExpiryTime(createSubscriptionRequest.getEnding());
        razorpaySubscriptionRequest.setStartTime(createSubscriptionRequest.getStarting());
        razorpaySubscriptionRequest.setQuantity(createSubscriptionRequest.getChargeCount());
        razorpaySubscriptionRequest.setTotalCount(createSubscriptionRequest.getTotalNumberOfBillingCycles());

        RazorpayCustomerContactDetails razorpayCustomerContactDetails = new RazorpayCustomerContactDetails();
        razorpayCustomerContactDetails.setPhoneNumber(createSubscriptionRequest.getPhone());
        razorpayCustomerContactDetails.setEmail(createSubscriptionRequest.getEmail());
        razorpaySubscriptionRequest.setRazorpayCustomerContactDetails(razorpayCustomerContactDetails);

        RazorpayPlanRequest razorpayPlanRequest = new RazorpayPlanRequest();
        razorpayPlanRequest.setFrequency(createSubscriptionRequest.getPlanPeriod().name());
        razorpayPlanRequest.setName(createSubscriptionRequest.getPlanTitle());
        razorpayPlanRequest.setDescription(createSubscriptionRequest.getPlanDescription());
        razorpayPlanRequest.setAmount(createSubscriptionRequest.getPlanAmount());
        razorpaySubscriptionRequest.setRazorpayPlanRequest(razorpayPlanRequest);
        return razorpaySubscriptionRequest;
    }
}
