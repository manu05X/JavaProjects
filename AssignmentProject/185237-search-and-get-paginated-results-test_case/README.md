# Search Users based on different Criteria and Get Results in pages

You need to add functionality for searching Users based on different criteria

## Requirements

 - You need to add following APIs in UserSearchController
     
      - An Api with path `/search` to search users by address accepting body in form of UserSearchRequestDto and returning `List<User>`
      - An Api with path `search/ladies` to get details of all Ladies accepting `pageNumber` with datatype Integer as query parameter and returning `List<User>`
      - An Api with path `search/adultMales` to get details of all Men having age >= 18 accepting `pageNumber` with datatype Integer as query parameter and returning `List<User>`
 - You need to add implementation in methods present in UserSearchService, taking help from UserRepository.

## Hints
 - Nothing is needed from your side in pom.xml or application.properties
 - No new file need to be created.
 - Nothing need to be added/removed in dtos and models.
 - No new field need to be added, No field need to be removed or modified.
 - If you will try to run testcases without providing solutions, all Testcases will fail.