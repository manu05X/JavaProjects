# Create a Subscription Link using Razorpay

## Requirements

You need to integrate Razorpay `Create a Subscription Link` API

You need to add an API with path `/subscription` in SubscriptionController which will take request in form of CreateSubscriptionRequest and return Subscription Link (String)

You need to add Implementation in SubscriptionService method `String createSubscription(CreateSubscriptionRequest subscriptionRequest)`. As a part of implementation, you also need to add mapping logic in `RazorpaySubscriptionRequest from(CreateSubscriptionRequest createSubscriptionRequest)` present in SubscriptionRequestConverter. Please go through Razorpay documentation and understand API contract to get which field will map to which field.

You also need to implement `Subscription createSubscriptionLink(RazorpaySubscriptionRequest subscriptionInput)` method in RazorpayPaymentGatewayClient taking help from Razorpay Official Documentation and RazorpayClient bean present in RazorpayConfig.

You need not to do any changes in `dtos` and `config`. Just refer them for better understanding.

## Hints

 - Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and razorpayId and razorpaySecret are already set in application.properties.
 - No new file need to be created.
 - If you will try to run testcases without providing solution, all Testcases will fail.
 - You can refer to [link](https://razorpay.com/docs/api/payments/subscriptions/create-subscription-link/) for help.