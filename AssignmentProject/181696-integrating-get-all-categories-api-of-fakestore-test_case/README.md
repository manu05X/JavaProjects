# Integrating Get all Categories API of FakeStore

## Requirements

### You need to call `Get all Categories` API provided by `FakeStore` - https://fakestoreapi.com/docs#p-categories and convert it's response int your model `Category` and return to your client.

1. Test out `Get all categories` API from above link in Postman and understand what type of response it is returning.
2. You need to add implementation in `List<Category> getCategories()` method present inside file `FakeStoreCategoryService`. Here you will use `getForEntity` method provided by `RestTemplate` to get response from FakeStore and then convert that response into list of Categories and return to  CategoryController. while converting you can generate Random Long number and assign it to `id`. Please make sure `id` field is not kept null.
3. You need to add API as well in `CategoryController` which will return `List<Category>` to client. Don't add any route/path to this API. It will consider route from @RequestMapping.
4. You don't need to change anything in model `Category` and interface `ICategoryService`. Don't change Dependencies injected in class as well.

## Hints
1. You don't need to create any new file.
2. Dependencies are added in each class for you help/reference, please don't remove or edit them.
3. If you will try to run testcases without adding solution, multiple Testcases will fail.