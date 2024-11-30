package org.example.evaluations.clients;

import org.json.JSONObject;
import org.mockito.ArgumentMatcher;

public class PlanJsonObjectMatcher implements ArgumentMatcher<JSONObject> {
    final String name;
    final Double amount;
    final String currency;
    final String description;

    public PlanJsonObjectMatcher(String name,Double amount,String currency,String description) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.name = name;
    }

    @Override
    public boolean matches(JSONObject jsonObject) {
        return
        name.equals(jsonObject.optJSONObject("item").optString("name", ""))
                && amount.equals(jsonObject.optJSONObject("item").optDouble("amount"))
                && description.equals(jsonObject.optJSONObject("item").optString("description", ""))
                && currency.equals(jsonObject.optJSONObject("item").optString("currency", ""));
    }
}
