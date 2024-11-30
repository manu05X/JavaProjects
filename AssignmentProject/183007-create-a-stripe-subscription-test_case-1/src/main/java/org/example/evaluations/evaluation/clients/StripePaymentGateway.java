package org.example.evaluations.evaluation.clients;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class StripePaymentGateway {

    @Value("${stripe.key}")
    public String apiKey;

    private final Long trialDays = 730L;

    public String createSubscriptionForProduct(String customerName,String customerEmail,Long productAmount, String productName, PlanCreateParams.Interval interval) {
        Stripe.apiKey = this.apiKey;
        try {
            Customer customer = getCustomer(customerName,customerEmail);
            Plan plan = getPlan(productName, interval, productAmount);

            SubscriptionCreateParams params =
                    SubscriptionCreateParams.builder()
                            .setCustomer(customer.getId())
                            .addItem(
                                    SubscriptionCreateParams.Item.builder().setPlan(plan.getId())
                                            .build()
                            ).setTrialFromPlan(true)
                            .build();
            Subscription subscription = Subscription.create(params);
            return subscription.getId();
        }catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private Product getProduct(String productName) {
        try {
            ProductCreateParams productCreateParams =
                    ProductCreateParams.builder().setName(productName).build();
            Product product = Product.create(productCreateParams);
            return product;
        }catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private Plan getPlan(String productName, PlanCreateParams.Interval interval, Long planAmount) {
        try {
            PlanCreateParams planCreateParams = PlanCreateParams.builder().setTrialPeriodDays(trialDays).setProduct(getProduct(productName).getId()).setInterval(interval).setAmount(planAmount)
                    .setCurrency("usd").build();
            Plan plan = Plan.create(planCreateParams);
            return  plan;
        }catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private Customer getCustomer(String customerName,String customerEmail) {
        try {
            CustomerCreateParams params =
                    CustomerCreateParams.builder()
                            .setName(customerName)
                            .setEmail(customerEmail)
                            .build();
            Customer customer = Customer.create(params);
            return customer;
        } catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
