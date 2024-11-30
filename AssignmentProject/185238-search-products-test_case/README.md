# Search Products 

You need to add functionality for searching Products based on different criteria in different situations

## Requirements

 - You need to add following APIs in ProductController
     
      - An Api with path `/products/{actualWeight}/{weightLimit}` to find our products which are costlier and lighter than others , which can be transferred to Hand Bag from Luggage because actual weight of products in Luggage has surpassed allowed weight limit. You just need to provide him this sorted list, He will pick himself. This Api will take pageNumber and pageSize as query parameters and return `Slice<Product>`.
      - An Api with path `/products/category/{category}` to find out products belonging to particular category and these should be displayed in order Newer to Older. This Api will take pageNumber and pageSize as query parameters and return `Slice<Product>`.
      - An Api with path `/products/search` to find products by search query user has passed in and return in sorted order of parameter provided by Cx. This Api will accept body in form of ProductQueryDto and return `Page<Product>`.
 - You need to add implementation in methods present in ProductSearchService, taking help from ProductRepository.

## Hints
 - Nothing is needed from your side in pom.xml or application.properties
 - No new file need to be created.
 - Nothing need to be added/removed in dtos and models.
 - No new field need to be added, No field need to be removed or modified.
 - If you will try to run testcases without providing solutions, all Testcases will fail.