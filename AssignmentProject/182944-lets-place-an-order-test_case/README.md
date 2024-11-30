# Let's place an order !!

## Requirements

You are provided with few models which are required while placing an Order

Fields have been already added in each class. `You need not to add any new field` . You just need to anotate those fields with relevant Cardinalities like @ManyToOne, @OneToOne etc...

In case, mapping table is needed between any 2 classes, Then Naming Convention for that mapping table and foreign keys should be like this as explained in below example -

Let's say we have 2 classes `user` and `role` and mapping table need to be created for their relation, so name of mapping table will be like `users_roles` and name of foreign keys be `user_id` , `role_id`.

`Connect 2 or more words with _ and only mapping table name has words in plural, not other tables`

`** Above given is just example for your reference, please don't create classes with name user, role **`

We have testcases defined which will check for which all tables are created with what names and what columns, so please follow naming convention rules seriously.

Note ->

You can also check which tables with what fields are created in H2 by running Application in IntellIJ and opening  `http://localhost:8080/h2-console` on browser and put values as below
- Saved Settings: `Generic H2(Embedded)`
- Setting Name: `Generic H2(Embedded)`
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:class11_ques4`
- User Name: `sa`
- Password: `password`
- click Connect

You also need to add implementation in `Order createOrder(Map<Long,Long> itemQuantityMap, Long customerId) throws ShortInventoryException` method presnt in OrderService as per rules

 - You are provided with Map<Long,Long> itemQuantityMap, where key denotes itemId and value denotes quantity of that item which need to be ordered.
 - Using this map, you need to construct ItemDetails and persist into DB.
 - For each item, you also need to update Inventory based on quantity ordered. If count in Inventory is less than Quantity Ordered, you need to throw `ShortInventoryException with Message - Ordered Quantity is not Available`.
 - The total cost of Order will be calculated by adding (item.price * quantity). 
 - You also need to create OrderStateTimeMapping for this order and persist in DB.
 - You are provided with customerId, based on that populate Customer field In Order and persist in DB.
 - Whatever repos you need, are already provided.

You also need to add an API for creation of Order which will take Body in form of CreateOrderRequestDto and return created Order. Also add ExceptionHandler for ShortInventoryException which will return status code 500 and exception message in Response Body.

You may need to define Loading Type(Fetch-Type) at some places to make `CreateOrder` method works properly.

You need not to do anything in `dtos` , `exceptions` and `repos` package. Just refer them for your understanding.

## Hints

- Nothing is needed from your side in pom.xml or application.properties
- No new file need to be created.
- No new field need to be added, No field need to be removed or modified.
- If you will try to run testcases without providing solution, all Testcases will fail.
- Since `order` is reserved keyword, create it's table with name `ORDERS`

