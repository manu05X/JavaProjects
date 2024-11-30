# Storing Client Profile in Cache as soon as a new user signup

## Requirements

You are creating ClientSignup Service. As soon any new Client signs up, you store his details into Redis Cache and Database and next time whenever there is request for getting Client Details, either you get it from Redis Cache or from Database

- In SignupController, you need to implement an Api with path `/signup` accepting request in form of SignupRequestDto and returning created Client.
- In ClientController, you need to implement 2 Apis
        
     - `/client/id/{id}`  to get Client details based on client Id
     
     - `/client/email/{email}` to get Client details based on Client Email
- In SignupController, you need to add an exception handler for UserAlreadyExistsException, finally returning exception message.
- In ClientController, you need to add an exception handler for UserNotFoundException, finally returning exception message.
- These Exception classes are already present in exceptions folder. you just need to use them.
- In SignupService, implement signup method, throw UserAlreadyExistsException with message `Please try out with some other email.` if user already exists, otherwise create client and persist in redis cache with KEYS ("CLIENTS",clientEmail). Also persist in DB.
- In ClientService, implement getClientFromId method which will only check DB for Client, if not found throw UserNotFoundException with message `Please signup first`.
- Also implement getClientFromEmail method in ClientService which will get client from Redis cache, if not found throw UserNotFoundException with message `Please signup first`.
- You are given RedisConfig, you also need to create bean of redisTemplate.

## Hints

- Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and properties are already set in application.properties.
- If you will try to run testcases without providing solution, all Testcases will fail.
- Nothing need to be done in dtos, models, exceptions. Please refer them for your understanding.