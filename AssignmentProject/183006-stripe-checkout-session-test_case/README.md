# Create a Stripe Checkout Session

## Requirements

In SessionController, you need to implement an API with path `/session` which will take Body in form of CreateSessionDto and return SessionDto. This API will be responsible for creating a CheckoutSession object and returning it.

In SessionService, you need to complete `SessionDto createSession(String successUrl, List<Long> amounts, List<String> productNames, List<Long> quantities)` method

In StripePaymentGateway, please implement createSession Method getting help from [Stripe Official Documentation](https://docs.stripe.com/api/checkout/sessions/create). Please note that you are provided with Lists of ProductNames which need to be ordered in this session along with their amounts and quantities. Ith product will have it's name at Ith index in productNames list and amount at Ith index in amounts list and quantity at Ith index in quantities list.

You don't need to do changes in `dtos`. Just refer it for your understanding. Fields are already present in each class.

## Hints
 - Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and properties are already set.
 - No new file need to be created.
 - If you will try to run testcases without providing solution, all Testcases will fail.
