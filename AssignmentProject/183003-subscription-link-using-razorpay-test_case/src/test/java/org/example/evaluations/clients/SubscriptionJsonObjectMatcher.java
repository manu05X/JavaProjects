package org.example.evaluations.clients;

import org.json.JSONObject;
import org.mockito.ArgumentMatcher;

public class SubscriptionJsonObjectMatcher implements ArgumentMatcher<JSONObject> {
    final Long totalCount;
    final Long quantity;
    final Long start;
    final Long expiry;
    final String planId;
    final String offerId;
    final String phone;
    final String email;

    public SubscriptionJsonObjectMatcher(Long totalCount, Long quantity, Long start, Long expiry,String phone, String email, String planId, String offerId) {
        this.totalCount = totalCount;
        this.expiry = expiry;
        this.start = start;
        this.quantity = quantity;
        this.phone = phone;
        this.email = email;
        this.planId = planId;
        this.offerId = offerId;
    }

    @Override
    public boolean matches(JSONObject jsonObject) {
        if (jsonObject == null) {
            return false;
        }

        return email.equals(jsonObject.optJSONObject("notify_info").optString("notify_email", ""))
                && phone.equals(jsonObject.optJSONObject("notify_info").optString("notify_phone", ""))
                && totalCount.equals(jsonObject.optLong("total_count"))
                && start.equals(jsonObject.optLong("start_at"))
                && expiry.equals(jsonObject.optLong("expire_by"))
                && quantity.equals(jsonObject.optLong("quantity"))
                && planId.equals(jsonObject.optString("plan_id"))
                && offerId.equals("offer_JTUADI4ZWBGWur");
    }
}