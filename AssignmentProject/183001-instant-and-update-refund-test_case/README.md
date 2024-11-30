# Issue an instant Refund and Update Refund

## Requirements

You need to integrate Razorpay `Create an Instant Refund` and `Update a Refund` API

In RefundController, you need to add APIs 
 - with path `/issueRefund` which will take request in form of RefundRequestDto and return RefundId (String)
 - with path `/updateRefund/{refundId}` which will take request in form of RefundRequestDto and return RefundId (String)
 - In `/updateRefund/{refundId}`, make sure that you are validating each parameter in body and accordingly constructing JSONObject for passing to Service layer.

You need to add Implementation in RefundService method `String issueRefund(Double amount, String receipt)` and `String updateRefund(String refundId, JSONObject jsonObject)`.

You also need to implement issueInstantRefund and updateRefund method in RazorpayPaymentGatewayClient taking help from Razorpay Official Documentation and Razorpay Client bean created in RazorpayConfig.

You need not to do any changes in `dtos` and `config`. Just refer them for better understanding.

## Hints

 - Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and properties are already set.
 - No new file need to be created.
 - If you will try to run testcases without providing solution, all Testcases will fail.
 - You can refer to [IssueRefundLink](https://razorpay.com/docs/api/refunds/create-instant/) and [UpdateRefundlink](https://razorpay.com/docs/api/refunds/update) for help.