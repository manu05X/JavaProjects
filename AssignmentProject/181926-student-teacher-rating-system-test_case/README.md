# Define relation between Student and Teacher where students can rate their teachers as well.

## Requirement

You are provided with 2 models - `Student` and `Teacher`. 
Let’s say we want to let students rate the teachers. A student can rate any number of teachers, and any number of students can rate the same teacher. Therefore, it’s a many-to-many relationship.

We need to store the rating score the student gave on the course. We can’t put it in the Student entity since a student can give different ratings to different teachers. Similarly, storing it in the Teacher entity wouldn’t be a good solution either.

This is a situation when the relationship itself has an attribute. Since we map DB attributes to class fields in JPA, we need a new entity class `TeacherRating` for the relationship. In this case, primary key of this new Entity class `TeacherRating` will be Composite Key.

You need to make sure that Tables are created for all 3 models `Student`, `Teacher` and `TeacherRating` and proper cardinalities are represented and `PrimaryKey` of `TeacherRating` is `CompositeKey`.

Note ->
 - In case, mapping table is needed between any 2 classes, Then Naming Convention for that mapping table and foreign keys should be like this as explained in below example -

 - Let's say we have 2 classes `user` and `role` and mapping table need to be created for their relation, so name of mapping table will be like `users_roles` and name of foreign keys be `user_id` , `role_id`.

 - `Connect 2 or more words with _ and only mapping table name has words in plural, not other tables`

 - `** Above given is just example for your reference, please don't create classes with name user, role **`

 - We have testcases defined which will check for which all tables are created with what names and what columns, so please follow naming convention rules seriously.

Note ->

You can also check which tables with what fields are created in H2 by running Application in IntellIJ and opening  `http://localhost:8080/h2-console` on browser and put values as below
 - Saved Settings: `Generic H2(Embedded)`
 - Setting Name: `Generic H2(Embedded)`
 - Driver Class: `org.h2.Driver`
 - JDBC URL: `jdbc:h2:mem:class8_ques3`
 - User Name: `sa`
 - Password: `password`
 - click Connect

## Hints

- You may need to create a new class for CompositeKey and use that in class `TeacherRating` 
- Nothing is needed from your side in pom.xml or application.properties
- If you will try to run testcases without providing solution, all Testcases will fail.