# Integrating Protected APIs - Real-Time Finance Data (Stock News, Currency News and Company Cash Flow) 

### PRE-REQUISITE FOR TESTING AND USING RAPIDAPI- https://rapidapi.com/hub
1. Signup on https://rapidapi.com/hub with your google account.
2. After that, it will ask for 3-4 basic things like name, experience and why do you want to use their website. Fill those details and you will land on their `API Marketplace` page.
3. Now try opening https://rapidapi.com/letscrape-6bRBa3QguO5/api/real-time-finance-data/playground/apiendpoint_9ac1efdb-4794-4c1a-bd3f-e88281c623ed. This is endpoint for `Stock News` on `Real-Time Finance Data`
4. Carefully copy your `X-RapidAPI-Key` visible on left side of page. This is needed for making all calls to RapidAPI.
5. On top Right Hand, you will see Blue Button - `Subscribe to Test`. On clicking, choose first option `Start Free Plan` and then hit `Subscribe` button. If some pop-up opens, click on `Skip,get started`.
6. Now you can test all Endpoints mentioned below which you need for this Assignment.
  - https://rapidapi.com/letscrape-6bRBa3QguO5/api/real-time-finance-data/playground/apiendpoint_9ac1efdb-4794-4c1a-bd3f-e88281c623ed. This is endpoint for `Stock News` on `Real-Time Finance Data`
  - https://rapidapi.com/letscrape-6bRBa3QguO5/api/real-time-finance-data/playground/apiendpoint_866ac3f7-39f0-4bf2-a87b-50dfbb20e23c . This is endpoint for `Currency News` on `Real-Time Finance Data`
  - https://rapidapi.com/letscrape-6bRBa3QguO5/api/real-time-finance-data/playground/apiendpoint_452121db-ffc1-4a4d-a476-0b00329f5258 . This is endpoint for `Company Cash Flow` on `Real-Time Finance Data`

## Requirements

1. You need to implement 2 GET APIs in `CompanyController` -
 - `/company/stockNews?symbol={stock_symbol}` taking searchQuery in form of String as input and returning `List<News>`. It will internally call `getStockNews` method of IStockService.
 - `/company/cashFlow?symbol={stock_symbol}` taking searchQuery in form of String as input and returning `List<CashFlow>`. It will internally call `getCompanyCashFlow` method of IStockService.
2. You need to implement 1 GET API in `CurrencyController` -
 - `/currency/conversionNews?from_symbol={currency1}&to_symbol={currency2}` taking searchQueries in form of String as input and returning `List<News>`. It will internally call `getCurrencyNews` method of ICurrencyService.
3. You also need to add implementation in `CompanyStockService` and `CompanyNewsService` methods. Details are 
  - In `getStockNews` , you need to make request at endpoint `https://real-time-finance-data.p.rapidapi.com/stock-news?symbol={symbol}`
  - In `getCompanyCashFlow` you need to make request at endpoint `https://real-time-finance-data.p.rapidapi.com/company-cash-flow?symbol={symbol}`
  - In `getCurrencyNews` you need to make request at endpoint `https://real-time-finance-data.p.rapidapi.com/currency-news?from_symbol={currency1}&to_symbol={currency2}`
  - Carefully Observe `dtos` and `models`. Classes are already given in which you will receive response from RapidAPI for each endpoint. No need to change anything in `models and dtos`.
  - (HINT) Please use RestTemplate's `exchange` method as you need to pass `X-RapidAPI-Key` in HttpHeaders and create HttpEntity through these HttpHeaders and pass this HttpEntity into exchange method.


## Hints
1. You don't need to create any new file.
2. Dependencies are added in each class for you help/reference, please don't remove or edit them.
3. If you will try to run testcases without adding solution, all Testcases will fail.

