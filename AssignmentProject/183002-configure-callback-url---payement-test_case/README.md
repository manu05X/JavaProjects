# Create Payment Link and configure CallBack URL

## Requirements

You need to integrate Razorpay `Create a Standard Payment Link` API and configure CallBack Url as well.

You need to add an API with path `/completePayment` in PaymentController which will take request in form of CompletePaymentDto and return PaymentLink.

You need to add Implementation in PaymentService method `String completePayment(String name, String phoneNumber, String email, Double amount, String description, String orderId, URL callback)`.

You also need to implement completePaymentAndOpenCallBack method in RazorpayPaymentGatewayClient taking help from Razorpay Official Documentation.

You also need to initialize RazorpayClient with help of `razorpayId` and `razorpaySecret` present in application.properties.

You need not to do any changes in `dtos`. Just refer them for better understanding.

## Hints

 - Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and `razorpayId` and `razorpaySecret` are already set in application.properties, just use them in bean initialization.
 - If you will try to run testcases without providing solution, all Testcases will fail.
 - You can refer to [link](https://razorpay.com/docs/api/payments/payment-links/create-standard) for help.