# Integrating deleteProduct and GetAllProductsOfCategory API of FakeStore

## Requirements
### You need to call `Get in category` API  - https://fakestoreapi.com/docs#p-category and  `Delete a product` API - https://fakestoreapi.com/docs#p-delete , both provided by `Fakestore`.

1. Test out both APIs from above links in Postman and understand what type of response these are returning.
2. You need to add implementation in `List<Product> getProducts(String categoryName)` method present inside file `FakeStoreCategoryService`. Here you will use `getForEntity` method provided by `RestTemplate` to get response from FakeStore and then convert that response into list of products and return to  CategoryController.
3. You need to add implementation in `void deleteProduct(Long productId)` method present inside file `FakeStoreProductService`. Here you will use `delete` method provided by `RestTemplate`.
4. You need to add API in `CategoryController` which will accept `categoryName` as input and return `List<Product>` to client.
5. You need to add API in `ProductController` which will accept `productId` of datatype `Long` as input and do not return anything.
6. You don't need to change anything in `models` , `dtos` and interfaces `ICategoryService` and `IProductService`. Don't change Dependencies injected in class as well.

## Hints
1. You don't need to create any new file.
2. Dependencies are added in each class for you help/reference, please don't remove or edit them.
3. If you will try to run testcases without adding solution, multiple Testcases will fail.