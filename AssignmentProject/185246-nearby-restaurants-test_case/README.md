# Storing and Retrieving nearby Restaurants through Redis Cache

## Requirements

You are using redis cache to store and retrieve nearby resturant details.

- In NearbyController, you need to implement 2 Apis with endpoint
        
     - `/nearby/restaurants/add`  to add restaurant accepting request in form of Restaurant and returning true if we were able to add restaurant into Redis Cache.     
     
     - `/nearby/restaurants` to get details of all nearby restaurants accepting request in form of UserLocationDto and returning `List<Restaurant>`.

- In NearbyService, implement both methods with help of Redis Hash and Geo Operations and class fields. First key to use while storing can be `LOCATION_KEY` and other key can be restaurantId.
- Please leverage arguments whatever provided in implementation.

- You are given RedisConfig, you also need to create bean of redisTemplate.

## Hints

- Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and properties are already set in application.properties.
- If you will try to run testcases without providing solution, all Testcases will fail.
- Nothing need to be done in dtos, models. Please refer them for your understanding.