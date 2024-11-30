package org.example.evaluations;

import org.json.JSONObject;
import org.mockito.ArgumentMatcher;

public class IssueRefundObjectMatcher implements ArgumentMatcher<JSONObject> {
    String receipt;
    String speed;
    Double amount;

    public IssueRefundObjectMatcher(String receipt,String speed,Double amount) {
        this.amount = amount;
        this.speed = speed;
        this.receipt = receipt;
    }

    @Override
    public boolean matches(JSONObject jsonObject) {
        if (jsonObject == null) {
            return false;
        }

        return amount.equals(jsonObject.optDouble("amount")) &&
                speed.equals(jsonObject.optString("speed")) &&
                receipt.equals(jsonObject.optString("receipt"));
    }
}
