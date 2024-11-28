# Integrating Protected APIs - Real-Time LinkedIn Scraper API (Search People by URL)

### PRE-REQUISITE FOR TESTING AND USING RAPIDAPI- https://rapidapi.com/hub
1. Signup on https://rapidapi.com/hub with your google account.
2. After that, it will ask for 3-4 basic things like name, experience and why do you want to use their website. Fill those details and you will land on their `API Marketplace` page.
3. Now try opening https://rapidapi.com/rockapis-rockapis-default/api/linkedin-data-api/playground/apiendpoint_7bb0e2ee-99d8-4f7a-a188-b2da4bf1df0f. This is endpoint for `Search People by URL` on `Real-Time LinkedIn Scraper API`
4. Carefully copy your `X-RapidAPI-Key` visible on left side of page. This is needed for making all calls to RapidAPI.
5. On top Right Hand, you will see Blue Button - `Subscribe to Test`. On clicking, choose first option `Start Free Plan` and then hit `Subscribe` button. If some pop-up opens, click on `Skip,get started`.
6. Now you can test this or any other Endpoint and see, what type of response it is giving.

## Requirements
1. You need to implement POST API in `LinkedInSearchController`. This API will accept Body in form of `LinkedInSearchRequest` and return `List<LinkedInSearchItem>`. Please check `models and dtos`. This API will call `searchPeople` method of ISearchService.
2. You also need to add implementation in `List<LinkedInSearchItem> searchPeople(LinkedInSearchRequest linkedInSearchRequest)` present inside LinkedInSearchService. Details are -
 - You need to use postForEntity method with Endpoint - `https://linkedin-data-api.p.rapidapi.com/search-people-by-url`. The response will be in form of `LinkedInSearchResult`. Since this is protected call and request and response both are of different datatype, `exchange method` you used in previous assignments will not work. In this case, you need to set interceptors while building RestTemplate Object. Please refer https://binarycoders.wordpress.com/2020/10/04/add-a-header-to-spring-resttemplate/#:~:text=add(%20new%20HeaderRequestInterceptor(%20%22X,setInterceptors(interceptors)%3B on how to achieve this. 
 - Please don't use any other way, otherwise TC will fail, There is a check whether you are setting interceptors or not.
3. No change is required in `models and dtos`. Please refer them for better understanding.

## Hints

1. You may need to create a new file for `HeaderRequestInterceptor` as per https://binarycoders.wordpress.com/2020/10/04/add-a-header-to-spring-resttemplate/#:~:text=add(%20new%20HeaderRequestInterceptor(%20%22X,setInterceptors(interceptors)%3B
2. Dependencies are added in each class for you help/reference, please don't remove or edit them.
3. If you will try to run testcases without adding solution, all Testcases will fail.