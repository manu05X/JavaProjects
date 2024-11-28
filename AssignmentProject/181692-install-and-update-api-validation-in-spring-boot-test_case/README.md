# Learning about RestController and Validations using Installs & Updates

## This project contains two Controllers - InstallController and UpdateController. You need to implement APIs in these controllers as per below requirements

## Requirements

1. In Install Controller 

       a. You need to add POST Api which will accept Body in form of InstallRequestDto and return InstallResponseDto to client. You can check out both InstallRequestDto and InstallResponseDto in dtos package. You explicitly need not to specify any path or route inside this POST Api.
       b. AppId in InstallRequestDto should be non-null. If it is null, you need to throw IllegalArgumentException with message - "AppId is null".
       c. After this validation, you need to call install method of IDeployService and get InstallStatus.
       d. Now create InstallReponseDto object with these parameters and return from this api.

2. In Updates Controller, you need to implement one POST and one GET Api

POST API
       
       a. You need to add POST Api which will accept Body in form of UpdateRequestDto and return UpdateResponseDto to client. You can check out both UpdateRequestDto and UpdateResponseDto in dtos package. You explicitly need not to specify any path or route inside this POST Api.
       b. AppId or Version in UpdateRequestDto should be non-null. If any one of them is null, you need to throw IllegalArgumentException with message - "Either AppId or version is missing".
       c. After this validation, you need to call update method of IDeployService and get UpdateStatus.
       d. Now create UpdateReponseDto object with appId, UpdateStatus and Version object which you will get by calling Parameterised Constructor present inside Version class. Pass version which you got in request inside that parameterised constructor.

GET API
 
        a. You need to add GET API with path "/{id}/installedVersion/{version}" which will return CheckUpdateResponseDto to client. Here datatype of id will be UUID and datatype of version will be String.
        b. You need to get LatestVersion by calling getLatestVersion method of IDeployService.
        c. If LatestVersion is null, then create an object of CheckUpdateResponseDto with AppId previded in request, NO_UPDATE status and set version to null in this CheckUpdateResponseDto object.
        d. If LatestVersion is not null, then create an object of CheckUpdateResponseDto with AppId previded in request, UPDATE_AVAILABLE status and set version to this LatestVersion in CheckUpdateResponseDto object.

## Hints

1. You don't need to create any new File. Controllers are already given with Routes. You just need to add APIs as mentioned in above requirements. 
2. Dependencies are added in each class, please don't remove or edit them.
3. If you will try to run testcases without adding solution, multiple Testcases will fail with different errors.
4. Please don't change anything in files present inside `models` ,`services` and `dtos` package or `ControllerAdvisor` present inside `controllers` package. Just refer them for your understanding.