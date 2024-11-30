# Storing Details of Viral Influencers into Redis Cache

## Requirements

You will be using redis cache to store details of those influencers whose total follower count > 500000 or total post reach > 5000000.


- In InfluencerController, you need to implement 3 Apis with endpoint
        
     - `/influencer`  to onboard new influencer accepting request in form of AddInfluencerDto and returning Boolean result telling whether we were able to add influencer into cache or not.
     
     - `/influencer` to get details of all viral influencers stored in Redis cache, returing result in form of `List<Influencer>`.
  
     - `/influencer/id/{id}` to check if a particular influencer's details are in cache or not. This Api will return Influencer with HttpStatus 200 OK if influencer found in cache, otherwise null with Http Status NOT_FOUND.

- In InfluencerService, implement all 3 methods with help of Redis Hash Operations and fields present in InfluencerService. First key to use while storing in Redis can be `VIRAL_INFLUENCER_REDIS_KEY` and second key can be influencer's id.
- Only store influencers who are either having follower count > MIN_FOLLOWERS_NEEDED_TO_VIRAL or totalPostReact > MIN_TOTAL_POST_REACH.

- You are given RedisConfig, you also need to create bean of redisTemplate.

## Hints

- Nothing is needed from your side in pom.xml or application.properties. Dependency is already added and properties are already set in application.properties.
- If you will try to run testcases without providing solution, all Testcases will fail.
- Nothing need to be done in dtos, models. Please refer them for your understanding.