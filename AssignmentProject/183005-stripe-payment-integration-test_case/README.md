# Get Payment Link and Call callbackUrl once payment is completed using Stripe

## Requirements

In PaymentController, you need to implement an API with path `/payment` which will take Body in form of InitializePaymentRequestDto and return PayLink (String). This API will be responsible for initiating Payment.

In PaymentService, you need to complete `String getPaymentLink(Long amount, Long quantity, String callbackUrl, String productName)` method

In StripePaymentGateway, please implement GetPaymentLink Method getting help from [Stripe Official Documentation](https://docs.stripe.com/api/payment-link/create)

You don't need to do changes in `dtos`. Just refer it for your understanding. Fields are already present in each class.

## Hints
 - Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and properties are already set.
 - No new file need to be created.
 - If you will try to run testcases without providing solution, all Testcases will fail.
