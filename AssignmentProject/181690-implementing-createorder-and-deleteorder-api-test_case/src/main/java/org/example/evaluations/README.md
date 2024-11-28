# Understanding Model View Controller (MVC) pattern by implementing CreateOrder and DeleteOrder Api

## This project contains OrderController, OrderService and OrderRepository. You need to add implementation wherever mentioned below in requirements or in Code comments.
### Please make sure you have solved 2nd Assignment before coming to this.

## Requirements

1. Please read about MVC Pattern if you don't know.
2. In OrderController,
     - You need to implement POST Api which will accept body in form of `OrderRequestDto` and will return created `Order`. This API will call `createOrder` method present inside `IOrderService`.
     - You need to implement DELETE Api which will accept `orderId` of datatype UUID as an argument and return Boolean result to client. This API will call `deleteOrder` method present inside `IOrderService`
3. You need to add following implementation for `createOrder` method in `OrderService`
      - set TotalOrderAmount & CustomerId to whatever passed in request.
      - set OrderStatus to `CREATED`
      - CreatedAt and LastUpdatedAt should current Date value (you might have to use default constructor of Date class)
      - Id can be any random UUID
      - Persist created Order in `OrderRepository` by calling  it's save method
      - Return this created Order.
4. You also need to implement `deleteOrder` method in `OrderService` where you will  call `remove` method of OrderRepository and return it's result. 
4. IOrderService and OrderRepository are complete, you need not to do anything inside them. Just take a look at them to better understand flow of code.
5. You need to add SpringBoot Annotation like `@Service` in OrderService class to make sure Spring creates Bean for it.

## Hints
1. You don't need to create any new file.
2. Dependencies are added in each class, please don't remove or edit them.
3. If you will try to run testcases without adding solution, multiple Testcases will fail with different errors.
4. Please don't change anything in files present inside `models`, `dtos` and `repositories` package. 