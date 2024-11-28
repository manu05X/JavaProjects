# Implementing Controller,Service and Client for supporting multiple Operations on `Cart`

## Requirements

You need to integrate following APIs related to Cart provided by `FakeStore`
 - https://fakestoreapi.com/docs#c-single
 - https://fakestoreapi.com/docs#c-user-cart
 - https://fakestoreapi.com/docs#c-new
 - https://fakestoreapi.com/docs#c-update
 - https://fakestoreapi.com/docs#c-delete

1. Test out both APIs on Postman, to better understand request and response. We have already provided you dtos for request and response which you need.
2. You need to add implementation in all methods present in `FakeStoreClient` and `FakeStoreCartService`
3. Function Signatures and Arguments make it evident, what will be datatype of request and what will be return type in both Client and Service Layer
4. You may need to write `requestForEntity` - a generic method to make any type of HTTP request, taking help from `postForEntity` provided by `RestTemplate` and call this generic function from your methods present in `FakeStoreClient` to make API calls to FakeStore and get Response from there.
5. You are also provided with Mapper Functions in FakeStoreCartService, which you may want to use.
6. You need to add following APIs in CartController
  - GET `/{cartId}` taking cartId of datatype Long and returning `Cart`
  - GET `/user/{userId}` taking userId of datatype Long and returning `List<Cart>`
  - DELETE `/{cartId}` taking cartId of datatype Long and returning `Cart`
  - POST taking request body in form of `Cart` and returning created `Cart`
  - PUT `/{cartId}` taking cartId of datatype Long and request body in form of `Cart` and returning updated `Cart`
  - All these APIs will internally call methods of ICartService already added as dependency in CartController.
  - Don't change RequestMapping in CartController.
7. Please don't change anything in `models` , `dtos` and `ICartService`. Go through them to understand how to use those.

## Hints
1. You don't need to create any new file.
2. Dependencies are added in each class for you help/reference, please don't remove or edit them.
3. If you will try to run testcases without adding solution, all Testcases will fail.
