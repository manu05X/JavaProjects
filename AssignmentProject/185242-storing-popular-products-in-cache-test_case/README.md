# Storing Popular Products a little different way for faster retrieval in Cache

## Requirements

You are using redis cache as your data store for storing all products. But now, you also need to make retrieval of popular products like "iphone", "jordan" or "rolex" really faster in comparison to non-popular products. Design a technique using Redis Operations by which you can retrieve these popular products really quick as lot of requests will come for these products.

- In ProductController, you need to implement 2 Apis with endpoint
        
     - `/product`  to add product accepting request in form of ProductRequestDto and returning created Product.      
     
     - `/product` to get product details and returning in form of `Set<Product>`.

- In ProductService, implement both methods with help of Redis Operations and fields. First key to use while storing can be `REDIS_KEY_POPULAR_PRODUCT` or `REDIS_KEY_NORMAL_PRODUCT` and second key can be productId.
- Please consider this scenario for your implementation, as you guys know new Iphone has launched and lot of fans will be visiting site for checking product specs and checking iphone related accessories. So if customer is searching anything related to "iphone", it should be retrived faster from cache.

- You are given RedisConfig, you also need to create bean of redisTemplate.

## Hints

- Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and properties are already set in application.properties.
- If you will try to run testcases without providing solution, all Testcases will fail.
- Nothing need to be done in dtos, models. Please refer them for your understanding.
- You may need to use operationsForSet and operationsForHash both.