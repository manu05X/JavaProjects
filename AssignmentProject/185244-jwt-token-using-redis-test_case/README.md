# Implementing AuthService with Redis Cache

## Requirements

You are using redis cache for storing user tokens mapped through emailIds for faster retrieval.

You are storing user related info like email,password and id in DB as well.

- In AuthController, you need to implement 2 Apis with endpoint
        
     - `/login` accepting request in form of LoginRequestDto and returning `ResponseEntity<Boolean>`.      
     
     - `/validateToken` accepting request in form of ValidateTokenRequestDto and returning Boolean.

- In AuthenticationService, implement both methods with help of Redis Operation.
- In `login` method

  - For simplicity, you can think, user is doing login for first time, directly after signup. His data would be stored in DB when he did signup. 
  - You still need to first check if user credentials are present in DB or not.
  - Check if stored password in DB matches with password provided in input.
  - If any of these conditions is false, return false with HttpStatus.BAD_REQUEST.
  - After password has been validated, Generate JWT Token based on user claims (user,iat,exp). SecretKey bean is already present in AuthConfig and is already injected in this service, just use that.
  - Once you got token, store that token in Redis mapped to userEmailId.
  - Now return true along with token in headers and HttpStatus 200.

- In `validateToken` method
        
  - Try to get token based on userEmail, if token is not found, throw RuntimeException with message `Authentication Error !!`.
  - After that create JwtParser based on SecretKey injected in this file and try to get claims and try to compare expiry with current Timestamp. If token is expired, return false, else return true


- You are given RedisConfig, you also need to create bean of redisTemplate.

## Hints

- Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and properties are already set in application.properties.
- If you will try to run testcases without providing solution, all Testcases will fail.
- Nothing need to be done in dtos, models, AuthConfig and repos. Please refer them for your understanding.
- You may need to use operationsForValue.