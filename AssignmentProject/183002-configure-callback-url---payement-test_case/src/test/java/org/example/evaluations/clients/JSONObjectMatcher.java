package org.example.evaluations.clients;

import org.json.JSONObject;
import org.mockito.ArgumentMatcher;

public class JSONObjectMatcher implements ArgumentMatcher<JSONObject> {
    private final String expectedDescription;
    private final Double expectedAmount;
    private final String expectedName;
    private final String expectedEmail;
    private final String expectedPhoneNumber;

    private final Boolean expectedNotifyViaSms;

    private final Boolean expectedNotifyViaEmail;

    private final String expectedPolicyName;

    private final String expectedOrderId;

    private final String expectedCallBackUrl;

    private final String expectedCallBackUrlMethod;


    public JSONObjectMatcher(String expectedDescription, Double expectedAmount,String expectedName,String expectedEmail,String expectedPhoneNumber,Boolean expectedNotifyViaSms,Boolean expectedNotifyViaEmail,String expectedPolicyName,String expectedOrderId,String expectedCallBackUrl, String expectedCallBackUrlMethod) {
        this.expectedDescription = expectedDescription;
        this.expectedAmount = expectedAmount;
        this.expectedName = expectedName;
        this.expectedEmail = expectedEmail;
        this.expectedNotifyViaSms = expectedNotifyViaSms;
        this.expectedNotifyViaEmail = expectedNotifyViaEmail;
        this.expectedPolicyName = expectedPolicyName;
        this.expectedPhoneNumber = expectedPhoneNumber;
        this.expectedCallBackUrl = expectedCallBackUrl;
        this.expectedCallBackUrlMethod = expectedCallBackUrlMethod;
        this.expectedOrderId = expectedOrderId;
    }

    @Override
    public boolean matches(JSONObject jsonObject) {
        if (jsonObject == null) {
            return false;
        }

        return expectedName.equals(jsonObject.optJSONObject("customer").optString("contact", ""))
                && expectedEmail.equals(jsonObject.optJSONObject("customer").optString("email", ""))
                && expectedPhoneNumber.equals(jsonObject.optJSONObject("customer").optString("name", ""))
                && expectedAmount.equals(jsonObject.optDouble("amount"))
                && expectedDescription.equals(jsonObject.optString("description"))
                && expectedOrderId.equals(jsonObject.optString("reference_id"))
                && expectedNotifyViaSms.equals(jsonObject.optJSONObject("notify").optBoolean("sms"))
                && expectedNotifyViaEmail.equals(jsonObject.optJSONObject("notify").optBoolean("email"))
                && expectedPolicyName.equals(jsonObject.optJSONObject("notes").optString("policy_name",""))
                && expectedCallBackUrl.equals(jsonObject.optString("callback_url"))
                && expectedCallBackUrlMethod.equals(jsonObject.optString("callback_method"));
    }
}