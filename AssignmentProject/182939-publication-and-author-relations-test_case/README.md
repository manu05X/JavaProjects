# Defining relation between different types of Publication and Author

## Requirements

Suppose your database consist of one table for storing all Publications. 

You are provided with 2 Types of Publication - `Book` and `BlogPost`, you need to make sure that their info also get represented by existing `PUBLICATION` Table.

You also need to define relation between `Author` and  `Publication`.

Fields are provided in each model, you need not to add/remove/modify any field.

Make Sure All Required Tables are created with proper relation

 - In case, mapping table is needed between any 2 classes, Then Naming Convention for that mapping table and foreign keys should be like this as explained in below example -

 - Let's say we have 2 classes `user` and `role` and mapping table need to be created for their relation, so name of mapping table will be like `users_roles` and name of foreign keys be `user_id` , `role_id`.

 - `Connect 2 or more words with _ and only mapping table name has words in plural, not other tables`

 - `** Above given is just example for your reference, please don't create classes with name user, role **`

 - We have testcases defined which will check for which all tables are created with what names and what columns, so please follow naming convention rules seriously.

## Testing

You can also check which tables with what fields are created in H2 by running Application in IntellIJ and opening  `http://localhost:8080/h2-console` on browser and put values as below
- Saved Settings: `Generic H2(Embedded)`
- Setting Name: `Generic H2(Embedded)`
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:class10_ques2`
- User Name: `sa`
- Password: `password`
- click Connect

## Hints

- Nothing is needed from your side in pom.xml or application.properties
- No new file need to be created.
- No new field need to be added, No field need to be removed or modified.
- If you will try to run testcases without defining relations, all Testcases will fail.