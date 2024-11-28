# Learn how to provide optional and required Query Params in your APIs

## In Scaler, there are 2 types of Contractual Employees - Instructor and Ta. Both Roles have certain level of permissions And there is a criteria to determine if a new ContractualEmployee is onboarded or not.


## Requirements

0. If you don't know, how to provide query params in APIs, recommend you to read about `@RequestParam`.
1. You just need to implement 3 GET APIs in `ContractualEmployeeController`. These 3 APIs will be internally calling methods of IEmployeeService to get details. Nothing need to done in EmployeeService. You can refer to methods and implementation present inside `EmployeeService` to get better idea.
2. These APIs need to be added in `ContractualEmployeeController` class  -:
     
      - GET  `/contractualEmployee/isOnboarded/email/{email}?empId={id}` - Here `empId` is optional query Parameter of datatype Long, which means it can be skipped as well. This Api will return Boolean Result.
      - GET  `/contractualEmployee/permissions?roles=val1,val2...` - Here roles is required query Parameter and we can provide multiple values in roles, so better consume roles through `List<String>`. This API will return `List<Permission>`. Permission model is already present inside models package.
      - GET `/contractualEmployee?email={email}&password={password}&name={name}` - Here all email,password and name are optional query parameters which means, all 3 parameters can be skipped as well. This Api will return Boolean result.
   
3. Please note route/path given in RequestMapping over `ContractualEmployeeController` to get what will be actual routes for your APIs.

## Hints
1. You don't need to create any new file.
2. Please don't change any dependency in any file.
3. If you will try to run testcases without adding solution, all Testcases will fail.
4. Please take a look into `models` and `services` to get better idea. Please don't modify any file in these packages.