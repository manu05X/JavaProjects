# Implementing Controller, Service and Repo for supporting multiple Operations on Category

## Requirements
1. You are provided with file `CategoryRepo`. You need to convert this file into Repository and `StorageCategoryService` will store and retrieve all data from Database using this Repository.
2. H2 is already been integrated with this project, so you need not to do anything except converting `CategoryRepo` into Repository for DB Integration.
3. You also need to add implementation in all methods present inside `StorageCategoryService`, All these methods will get data from `CategoryRepo`.
4. Methods present inside `StorageCategoryService` are
  - `Category addCategory(Category category)` - This method should first check if category with same id is already present in DB or not. If not present, then only save into DB.
  - `List<Category> getAllPremiumCategories()` - This method will return all the categories having value of `isPremium` field as true.
  - `List<Category> getCategoriesBetweenIds(Long categoryId1,Long categoryId2)` - This will return all the categories whose id lie in between categoryId1 and categoryId2
  - `List<Category> getCategoriesWithMatchingName(String categoryName)` -  This will return all categories whose name matches with name passed in method argument.
  - For all these operations, straight away functions are provided by JpaRepository. So your `CategoryRepo` will extend `JpaRepository`
5. You also need to implement following APIs in `CategoryController`. It will just internally call methods provided by `ICategoryService`.
  - POST API with path `/category` accepting body in form of Category and returning created Category or already existing category.
  - GET API with path `/category/premium` returning `List<Category>`
  - GET API with path `/category/{categoryId1}/{categoryId2}` accepting categoryId1 and categoryId2 in form of Long and returning `List<Category>`
  - GET API with path `/category/{categoryName}` accepting categoryName in form of String and returning `List<Category>`
6. Please don't change anything in `models` and `ICategoryService`. Go through them to understand how to use those.

## Hints
1. Dependencies are added in each class for you help/reference, please don't remove or edit them.
2. If you will try to run testcases without adding solution, all Testcases will fail.