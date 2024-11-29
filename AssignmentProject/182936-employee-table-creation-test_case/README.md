# Creating Tables for different types of Employees present in Scaler

## Requirements

You are provided with Model Classes for different types of Employees. 

As you guys are aware that, Instructor and Ta are hired on contract basis normally while SoftwareDeveloper and ProgramManager have more defined need and charter

You need to make sure that Tables are created for all concrete classes and characteristics of Superclass will be present in all inherited classes throughout Hierarchy.

Note - Primary Key for ContractualEmployees will be alias and for PermanentEmployees will be email.

## Testing

You can also check which tables with what fields are created in H2 by running Application in IntellIJ and opening  `http://localhost:8080/h2-console` on browser and put values as below
- Saved Settings: `Generic H2(Embedded)`
- Setting Name: `Generic H2(Embedded)`
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:class10_ques5`
- User Name: `sa`
- Password: `password`
- click Connect

## Hints

- Nothing is needed from your side in pom.xml or application.properties
- No new file need to be created.
- No new field need to be added, No field need to be removed or modified.
- If you will try to run testcases without defining relations, all Testcases will fail.