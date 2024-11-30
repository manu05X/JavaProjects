# Create a Payout to a Bank Account

## Requirements

You need to integrate Razorpay `Create a Payout to a Bank Account` API

You need to add an API with path `/payout` in PayoutController which will take request in form of PayoutRequestDto and return PayoutId (String)

You need to add Implementation in PayoutService method `String createPayoutToBankAccount(String accountNumber, Double amount, PayoutPurpose purpose, String referenceId, String narration)`.

You also need to implement `createPayoutToBankAccount` method in RazorpayPaymentGatewayClient taking help from Razorpay Official Documentation.

You need not to do any changes in `dtos`. Just refer them for better understanding.

## Hints

 - Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and properties are already set.
 - No new file need to be created.
 - If you will try to run testcases without providing solution, all Testcases will fail.
 - You can refer to [link](https://razorpay.com/docs/api/x/payouts/create/bank-account/) for help. Read it thoroughly.
 - You can use RestTemplateBuilder for calling Razorpay API.