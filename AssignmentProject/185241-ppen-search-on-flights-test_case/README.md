# Search Flights where user query matches any field

You need to implement functionality where Cx will search with particular query and we need to give him all flights where his query matches any of flight attribute be it source, id, airline etc.. 

## Requirements

 - You need to add an API in SearchController
     
      - An Api with path `/search/{query}` to search flights matching query provided by user and return response in form of `Page<Flight>`. pageSize and pageNumber will be consumed as query parameters.
      
 - You need to add implementation of `getFlightsMatchingSearchQuery` present in OpenSearchService, taking help from FlightRepository. 

    - In implementation, please make sure you are checking user provided query string with all attributes of Flight. 
    - Also In case, user send time in search query, then we need to return flights +- 3 hours from that time. 
    - For simplicity, you can think, if user sends time in search query, it will be in format `HH:MM:SS`. 

## Hints
 - Nothing is needed from your side in pom.xml or application.properties
 - No new file need to be created.
 - Nothing need to be added/removed in dtos and models.
 - No new field need to be added, No field need to be removed or modified.
 - If you will try to run testcases without providing solutions, all Testcases will fail.