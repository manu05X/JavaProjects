package org.example.evaluations;

import org.json.JSONObject;
import org.mockito.ArgumentMatcher;

public class UpdateRefundObjectMatcher implements ArgumentMatcher<JSONObject> {
    String receipt;
    String speed;
    Double amount;

    public UpdateRefundObjectMatcher(String receipt, String speed, Double amount) {
        this.amount = amount;
        this.speed = speed;
        this.receipt = receipt;
    }

    @Override
    public boolean matches(JSONObject jsonObject) {
        if (jsonObject == null) {
            return false;
        }

        if(amount != null && !amount.equals(jsonObject.optDouble("amount"))) return false;
        if (speed != null && !speed.equals(jsonObject.optString("speed"))) return  false;
        if (receipt != null && !receipt.equals(jsonObject.optString("receipt"))) return  false;
        return true;
    }
}
