# Defining Relation between InstagramComment, InstagramLike, InstagramPage, InstagramPost and InstagramUser

## Requirement

You are given 5 classes in `models` package  - `InstagramComment`, `InstagramLike`, `InstagramPage`, `InstagramPost` and `InstagramUser`

Your task is to `define relations` between all these classes  and make sure `tables are created` for these models.

Fields have been already added in each class. `You need not to add any new field` . You just need to anotate those fields with relevant Cardinalities like @ManyToOne, @OneToOne etc...


Note ->

You can also check which tables with what fields are created in H2 by running Application in IntellIJ and opening  `http://localhost:8080/h2-console` on browser and put values as below
 - Saved Settings: `Generic H2(Embedded)`
 - Setting Name: `Generic H2(Embedded)`
 - Driver Class: `org.h2.Driver`
 - JDBC URL: `jdbc:h2:mem:class8_ques2`
 - User Name: `sa`
 - Password: `password`
 - click Connect


## Hints

- Nothing is needed from your side in pom.xml or application.properties
- No new file need to be created.
- No new field need to be added, No field need to be removed or modified.
- If you will try to run testcases without defining relations, all Testcases will fail.