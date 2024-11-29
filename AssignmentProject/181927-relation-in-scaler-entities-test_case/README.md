# Defining Relation between Batch, Class, Instructor and Learner in Scaler Ecosystem

## Requirement

You are given 4 classes in `models` package - `Batch`, `Class` , `Instructor` and `Learner`

Your task is to `define relations` between all these classes  and make sure `tables are created` for these models and `mapping tables` wherever applicable.

Fields have been already added in each class. `You need not to add any new field` . You just need to anotate those fields with relevant Cardinalities like @ManyToOne, @OneToOne etc...

In case, mapping table is needed between any 2 classes, Then Naming Convention for that mapping table and foreign keys should be like this as explained in below example -

Let's say we have 2 classes `user` and `role` and mapping table need to be created for their relation, so name of mapping table will be like `users_roles` and name of foreign keys be `user_id` , `role_id`.

`Connect 2 or more words with _ and only mapping table name has words in plural, not other tables`

`** Above given is just example for your reference, please don't create classes with name user, role **`

We have testcases defined which will check for which all tables are created with what names and what columns, so please follow naming convention rules seriously.

Note ->

You can also check which tables with what fields are created in H2 by running Application in IntellIJ and opening  `http://localhost:8080/h2-console` on browser and put values as below
- Saved Settings: `Generic H2(Embedded)`
- Setting Name: `Generic H2(Embedded)`
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:class8_ques4`
- User Name: `sa`
- Password: `password`
- click Connect

## Hints

- Nothing is needed from your side in pom.xml or application.properties
- No new file need to be created.
- No new field need to be added, No field need to be removed or modified.
- If you will try to run testcases without defining relations, all Testcases will fail.