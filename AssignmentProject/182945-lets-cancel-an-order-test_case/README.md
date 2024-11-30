# Let's cancel an order !!

#### Please attempt Assignment 4 before this.

## Requirements

You are provided with same models which were there in Assignment 4. You just need to add same annotations again which you added in Assignment 4. 

All Rules for cardinalities, Fetch-Type and naming tables and fields remain same as Assignment 4. 

Note ->

You can also check which tables with what fields are created in H2 by running Application in IntellIJ and opening  `http://localhost:8080/h2-console` on browser and put values as below
- Saved Settings: `Generic H2(Embedded)`
- Setting Name: `Generic H2(Embedded)`
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:class11_ques5`
- User Name: `sa`
- Password: `password`
- click Connect

You also need to add implementation in `Boolean cancelOrder(Long orderId) throws OrderNotFoundException` method present in OrderService as per rules

 - Get Order from DB based on OrderId, If order is not present, `throw OrderNotFoundException with message - orderId is wrong`
 - Get ItemDetails saved in DB corresponding to that Order and delete them. Also update Inventory back.
 - Create OrderStateTimeMapping with cancelled order state for this Order and persist into DB.
 - Also get Customer who has cancelled the order and update it's OrderCancellationCount and persist into DB.   Note - We have added a new field orderCancellationCount in Customer class.
 - Finally return true;

You also need to add an API for cancellation of Order which will take Body in form of CancelOrderRequestDto and return `true` result in Non-Exception Scenarios. Also add ExceptionHandler for OrderNotFoundException which will return status code 400 and exception message in Response Body.

You need not to do anything in `dtos` , `exceptions` and `repos` package. Just refer them for your understanding.

## Hints

- Nothing is needed from your side in pom.xml or application.properties
- No new file need to be created.
- No new field need to be added, No field need to be removed or modified.
- If you will try to run testcases without providing solution, all Testcases will fail.
- Since `order` is reserved keyword, create it's table with name `ORDERS`
- Since you are deleting few entries from DB, so it's not POST API.