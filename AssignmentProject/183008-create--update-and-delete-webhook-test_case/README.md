# Create, Update and Delete Webhook on Stripe

## Requirements

In WebhookController, you need to implement 3 APIs

 - An API with path `/webhook` to create Webhook accepting body in form of WebhookDto and returning created Webhook (present in dtos).
 - An API with path `/webhook/{id}` to delete Webhook which will return Boolean result.
 - An API with path `/webhook/{id}` to update existing Webhook accepting body in form of WebhookDto and returning updated Webhook (present in dtos).

In WebhookService, you need to  complete all 3 methods.

In StripePaymentGateway, please implement all 3 Method. You can take help from [Link](https://docs.stripe.com/api/webhook_endpoints)

You don't need to do changes in `dtos`. Just refer it for your understanding. Fields are already present in each class.

## Hints
 - Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and properties are already set.
 - No new file need to be created.
 - If you will try to run testcases without providing solution, all Testcases will fail.
