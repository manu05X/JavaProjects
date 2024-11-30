# Create a Stripe Subscription

## Requirements

In SubscriptionController, you need to implement an API with path `/subscription` which will take Body in form of SubscriptionRequestDto and return Id of created Subscription.

In SubscriptionService, you need to complete `String createSubscriptionForProduct(String customerName,String customerEmail,Long productAmount, String productName, PlanCreateParams.Interval interval)` method

In StripePaymentGateway, please implement `createSubscriptionForProduct` Method. You can take help from [Link](https://docs.stripe.com/api/subscriptions/create) , but exact logic will not help, as we also want to give free Trial for 730 days. Whatever information you need is provided as input, please use those parameters only and don't modify function signature.

You don't need to do changes in `dtos`. Just refer it for your understanding. Fields are already present in each class.

## Hints
 - Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and properties are already set.
 - No new file need to be created.
 - If you will try to run testcases without providing solution, all Testcases will fail.
