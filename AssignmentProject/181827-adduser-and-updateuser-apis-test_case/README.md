# Implementing AddUser and UpdateUser APIs

## Requirements
You need integrate `Add User` API - https://fakestoreapi.com/docs#u-new and `Update User` API - https://fakestoreapi.com/docs#u-update , both provided By `FakeStore`.

1. Test out both APIs on Postman, to better understand request and response. We have already provided you dtos for request and response which you need.
2. You need to add implementation in `User add(User user)` method and `User update(User user, Long userId)` method present inside `UserService`.
3. You may need to write `requestForEntity` - a generic method to make any type of HTTP request, taking help from `postForEntity` provided by `RestTemplate` and call this generic function from add and update to make API calls to FakeStore and get Response from there.
4. You are getting `Id` in response from FakeStore if `Add User` is successful. Please use this `Id` to set id of response user, which you will be returning to UserController.
5. Please use `HttpMethod.PUT` for update and `HttpMethod.POST` for add.
6. You are also provided with Mapper Functions in both UserController and UserService, which you may want to use.
7. You need to add following APIs in UserController
- [POST] API to add `user` accepting Body in form of UserRequestDto from client and returning created User to Client.  
- [PUT] API to update `user` accepting `userId` of datatype Long and Body in form of UserRequestDto from client and returning created User to Client.
- Don't change anything in @RequestMapping added over controller.
- Both will call methods provided by IUserService.
8. Please don't change anything in `dtos` , `models` and `IAuthService`


## Hints
1. You don't need to create any new file.
2. Dependencies are added in each class for you help/reference, please don't remove or edit them.
3. If you will try to run testcases without adding solution, all Testcases will fail.
 