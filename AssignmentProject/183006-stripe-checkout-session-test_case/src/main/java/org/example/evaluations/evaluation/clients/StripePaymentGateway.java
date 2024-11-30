package org.example.evaluations.evaluation.clients;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.checkout.Session;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.example.evaluations.evaluation.dtos.SessionDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StripePaymentGateway {

    @Value("${stripe.key}")
    public String apiKey;

    public SessionDto createSession(String successUrl, List<Long> amounts, List<String> productNames, List<Long> quantities) {
        Stripe.apiKey = apiKey;
        try {
            List<Price> prices = new ArrayList<>();
            for(int i=0; i<productNames.size(); i++) {
                prices.add(getPrice(amounts.get(i),productNames.get(i)));
            }
            List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
            for(int i=0;i<prices.size();i++) {
                lineItems.add(SessionCreateParams.LineItem.builder()
                        .setPrice(prices.get(i).getId())
                        .setQuantity(quantities.get(i))
                        .build());
            }
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setSuccessUrl(successUrl)
                            .addAllLineItem(lineItems)
                            .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                            .build();
            Session session = Session.create(params);
            SessionDto sessionDto = new SessionDto();
            sessionDto.setId(session.getId());
            sessionDto.setUrl(session.getUrl());
            sessionDto.setExpiry(session.getExpiresAt());
            sessionDto.setTotal(session.getAmountTotal());
            return sessionDto;
        }catch (StripeException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Price getPrice(Long amount, String productName) {
        try {
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("usd")
                            .setUnitAmount(amount)
                            .setRecurring(
                                    PriceCreateParams.Recurring.builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName(productName).build()
                            )
                            .build();

            Price price = Price.create(params);
            return price;
        }catch (StripeException exception) {
            throw new RuntimeException(exception);
        }
    }
}
