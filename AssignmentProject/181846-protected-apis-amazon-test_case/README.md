# Integrating Protected APIs - Real-Time Amazon Data (Product Search and Products By Category) 
 
### PRE-REQUISITE FOR TESTING AND USING RAPIDAPI- https://rapidapi.com/hub
1. Signup on https://rapidapi.com/hub with your google account.
2. After that, it will ask for 3-4 basic things like name, experience and why do you want to use their website. Fill those details and you will land on their `API Marketplace` page.
3. Now try opening https://rapidapi.com/letscrape-6bRBa3QguO5/api/real-time-amazon-data/playground/apiendpoint_17991940-c656-454f-a9ee-0277b0ada11d. This is endpoint for `Product Search` on `Real-Time Amazon Data`
4. Carefully copy your `X-RapidAPI-Key` visible on left side of page. This is needed for making all calls to RapidAPI.
5. On top Right Hand, you will see Blue Button - `Subscribe to Test`. On clicking, choose first option `Start Free Plan` and then hit `Subscribe` button. If some pop-up opens, click on `Skip,get started`.
6. Now you can test this or any other Endpoint and see, what type of response it is giving.

## Requirements

1. You need to implement 2 GET APIs in `AmazonProductController` - 
     - `/amazon/search?query={searchQuery}` taking searchQuery in form of String as input and returning `List<AmazonProduct>`. It will internally call `getProductByName` method of IProductService.
     - `/amazon/products-by-category?categoryid={catId}` taking categoryId in form of String as input and returning `List<AmazonProduct>`. It will internally call `getProductByCategoryId` method of IProductService.
2. You also need to add implementation in `AmazonProductService` methods. Details are 
  - In `getProductByName` , you need to make call at endpoint `https://real-time-amazon-data.p.rapidapi.com/search?query={name}`.
  - In `getProductByCategoryId`, you need to make call at endpoint `https://real-time-amazon-data.p.rapidapi.com/products-by-category?category_id={cid}` .
  - (HINT) Please use RestTemplate's `exchange` method as you need to pass `X-RapidAPI-Key` in HttpHeaders and create HttpEntity through these HttpHeaders and pass this HttpEntity into exchange method.
3. You are provided with `dtos` and `models` in which you will get response from RapidAPI. No change is needed in `models or dtos`.

## Hints

1. You don't need to create any new file.
2. Dependencies are added in each class for you help/reference, please don't remove or edit them.
3. If you will try to run testcases without adding solution, all Testcases will fail.

