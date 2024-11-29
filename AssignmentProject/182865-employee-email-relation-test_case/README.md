# Define relation between Corporate Employee and one Corporate Email Address

## Requirements

You are given with 2 models - `CorporateEmployee` and `CorporateEmailAddress`. Fields are already present in these models.

You need to define cardinalities like @ManyToOne, @OneToOne in these models and make sure tables are also created for these classes.

Also instead of creating new column `corporate_email_address_id`, we want to mark the Primary Key column `employee_id` of corporate_email_address table as the foreign key to corporate_employee table. This way, we will optimize the storage space. This is known as `Shared Primary Key`.

You also need to add support for that.

Note ->

You can also check which tables with what fields are created in H2 by running Application in IntellIJ and opening  `http://localhost:8080/h2-console` on browser and put values as below
- Saved Settings: `Generic H2(Embedded)`
- Setting Name: `Generic H2(Embedded)`
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:class8_ques1`
- User Name: `sa`
- Password: `password`
- click Connect


## Hints

- Nothing is needed from your side in pom.xml or application.properties
- No new file need to be created.
- No new field need to be added, No field need to be removed or modified.
- If you will try to run testcases without defining relations, all Testcases will fail.