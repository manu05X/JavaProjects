# Defining relation between different Products and how they can be effectively represented in Database

## Requirements

You are provided with different models. 

Your task is to effectively define relation between these models (Aggregation and Composition, whatever wherever makes sense) and need to make sure that Tables are created.

Status field should be represented by String data type in database.

## Testing

You can also check which tables with what fields are created in H2 by running Application in IntellIJ and opening  `http://localhost:8080/h2-console` on browser and put values as below
- Saved Settings: `Generic H2(Embedded)`
- Setting Name: `Generic H2(Embedded)`
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:class10_ques1`
- User Name: `sa`
- Password: `password`
- click Connect

## Hints

- Nothing is needed from your side in pom.xml or application.properties
- No new file need to be created.
- Some field may need to added somewhere.
- If you will try to run testcases without giving solution, all Testcases will fail.
