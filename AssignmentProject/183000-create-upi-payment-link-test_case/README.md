# Create UPI Payment Link

## Requirements

You need to integrate Razorpay `Create a UPI Payment Link` API

You need to add an API with path `/initiatePayment` in PaymentController which will take request in form of InitiatePaymentRequestDto and return PaymentLink (String)

You need to add Implementation in PaymentService method `String initiatePayment(String name, String phoneNumber, String email, Double amount,String description)`.

You also need to implement initiatePayment method in RazorpayPaymentGatewayClient taking help from Razorpay Official Documentation and Razorpay Client bean created in RazorpayConfig.

You need not to do any changes in `dtos` and `config`. Just refer them for better understanding.

## Hints

 - Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and properties are already set.
 - No new file need to be created.
 - If you will try to run testcases without providing solution, all Testcases will fail.
 - You can refer to [link](https://razorpay.com/docs/api/payments/payment-links/create-upi/) for help.