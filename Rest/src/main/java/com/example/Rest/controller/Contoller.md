# @GetMapping
___

- The client sends an HTTP request to the REST service. The dispatcher servlet handles the request and if the request has JSON data, the **HttpMessageConverter** converts it to **Java objects**. 
- The request is `mapped` to a `controller` which calls **service layer methods**. 
- The service layer `delegates the call` to repository and **returns the data as POJO**. The **MessageConverter** `converts` the data to `JSON` and it is sent back to the client.