# Delete Products Based On Multiple Criterias

## Requirement
H2 is already been integrated with this project. So you need not to do anything else for DB Integration except adding `ProductRepo` Repository.

You are provided with 2 Entities `Product` and `Category`. Both of them are related to each other.

1. You need to create repository with name `ProductRepo` and use straight away `available methods from JpaRespository` for below requirements
  - Get Products with particular Name
  - Delete Product with particular id
  - Delete all Products present in Database
  - Delete Products with specific Name and return count of deleted products
  - Delete Products with specific Category Name
 
2. In `ProductRepo`, you also need to provide queries for below cases, as Jpa doesn't provide straight away methods.
  - Delete Product if it's id matches with provided Category id. Keep method name as `void deleteProductWhereIdMatchesCategoryId(Long categoryId)`
  - Retain Products created after certain Date. This will also return count of deleted products. Keep method name as `int retainProductsAfter(Date retainDate)`
3. If all products belonging to particular Category are deleted, ultimately, that category should also get deleted. Similarly if we are onboarding a new product and if category is not present in DB already, a new Category should also get created along with Product. To make sure, both of these happen, you need to make some changes into `Product` Class. Please do that as part of this assignment.
4. You are also provided with `CategoryRepo` Nothing need to be done in it.


## Hints
1. New file `ProductRepo.java` should be created inside already present `repos` package.

2. If you will try to run testcases without adding solution, all Testcases will fail.