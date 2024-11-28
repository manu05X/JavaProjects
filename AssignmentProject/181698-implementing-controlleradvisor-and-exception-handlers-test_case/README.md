# Implementing ControllerAdvisor and Exception Handlers

## Requirements
You need to implement 3 ExceptionHandlers for different type of exceptions and those exception handlers will perform some action whatever mentioned below

1. You are given File `ControllerAdvisor`, You need to make it act like ControllerAdvisor for all your Rest Controllers. This file will contain all your exception handlers which you will be implementing based on below req
2. Add an Exception handler for `IllegalArgumentException` and `NullPointerException` and in case of these exceptions, handler will return exception message along with status code 400
3. Add an Exception handler for `IndexOutOfBoundsException` and in case of these exceptions, handler will return message `Something went bad at our side` along with status code 500
4. Add an Exception handler for `MethodArgumentTypeMismatchException` and `NumberFormatException`  and in case of these exceptions, handler will return message `Please pass userId or cartId in correct format` along with status code 409
5. You need not to do anything in `CartController` or `models`.

## Hints
1. You don't need to create any new file.
2. If you will try to run testcases without adding solution, multiple Testcases will fail.