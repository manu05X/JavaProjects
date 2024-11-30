# Find and Sort Employees 

You need to add functionality for finding Employees based on department and sorting data based on input provided by Admin.

## Requirements

 - You need to add following API in EmployeeFinderController
     
      - An Api with path `/employeeFinder` to find employees by department accepting body in form of EmployeeFinderRequestDto and returning `Page<Employee>`
      
 - You need to add implementation in `findEmployees` method present in EmployeeFinderService. This method is also taking list of Sort Params which Admin can provide. You need to return data in pages in sorted order. In case 2 employees are tieing at some sort criteria, then next sort criteria will be used to remove tie.

## Hints
 - Nothing is needed from your side in pom.xml or application.properties
 - No new file need to be created.
 - Nothing need to be added/removed in dtos and models.
 - No new field need to be added, No field need to be removed or modified.
 - If you will try to run testcases without providing solutions, all Testcases will fail.