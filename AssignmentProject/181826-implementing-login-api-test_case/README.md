# Implementing Login API

## Requirements

### You need to call `User Login` API provided by `FakeStore` - https://fakestoreapi.com/docs#a-login. In response, you will get Token, which you need to return to your client.

1. Test out `User Login` API - https://fakestoreapi.com/docs#a-login in Postman using credentials 
`{
   "username" : "kevinryan",
   "password" : "kev02937@"
   }` and see what type of response you are getting.

2. You need to add implementation in `MultiValueMap<String, String> login(FakeStoreLoginRequestDto fakeStoreLoginRequestDto)` method present inside file `AuthService`. Refer to `FakeStoreLoginRequestDto` and `FakeStoreLoginResponseDto` present inside `dtos` package. In case username and password are correct, you will get Token, Add that token with key `HttpHeaders.SET_COOKIE` in MultiValueMap and send to AuthController. In case username or password is incorrect, RestTemplate will throw `HttpClientErrorException`. In case of HttpClientErrorException, return null from this method.
3. You need to add an API with route `/login` in `AuthController` which will take Body in form of `FakeStoreLoginRequestDto` and return `ResponseEntity<String>` to client. If AuthService returns null, then put content as `login failed` with status `UNAUTHORIZED` and null headers , otherwise content `login successful` with status `OK` along with headers in ResponseEntity.
4. You don't need to change anything in `dtos` and interface `IAuthService`. Don't change Dependencies injected in class as well.


## Hints
1. You don't need to create any new file.
2. Dependencies are added in each class for you help/reference, please don't remove or edit them.
3. If you will try to run testcases without adding solution, all Testcases will fail.
