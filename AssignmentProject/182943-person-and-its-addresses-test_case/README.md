# Person and It's Addresses

## Requirements

You are provided with 2 models `Address` and `Person`. Fields are already present in these classes, You need to add Cardinalities and make sure tables are created and implement following functions present in Services.

 - `Pair<String,String> getCityAndPincodeFromAddressId(Long addressId)` present in AddressService
 - `Set<String> getAllUniqueCities()` present in PersonService - For all the persons present in DB, capture name of unique cities they have lived at.
 - `Set<String> getCitiesWherePersonLivedAt(Long personId)` - Capture name of cities uniquely at which person with particular id has lived.

You need to make sure that all these retrievals happen with minimum number of queries (in most optimised way).

## Hints
 - Nothing is needed from your side in pom.xml or application.properties
 - No new file need to be created, No field need to be added/modified/removed.
 - If you will try to run testcases without giving solution, all Testcases will fail.
 - Please don't change anything in file `CustomStatementInspector`