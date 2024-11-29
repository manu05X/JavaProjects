# Relating different type of Bank Accounts and their Cards

## Requirements

You are provided with 5 different models - `Account`, `CreditAccount`, `CreditCard`, `DebitAccount` and `DebitCard`

`Debit Account` and `Credit Account` are 2 different types of Accounts. So we want to store all common attributes related to Account in `Account` table and `CreditAccount` and `DebitAccount` will refer to their attributes in `Account` table using `account_id`.

You also need to define relation between different type of Accounts and their Cards and make sure tables are created for each.

## Testing

You can also check which tables with what fields are created in H2 by running Application in IntellIJ and opening  `http://localhost:8080/h2-console` on browser and put values as below
- Saved Settings: `Generic H2(Embedded)`
- Setting Name: `Generic H2(Embedded)`
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:class10_ques3`
- User Name: `sa`
- Password: `password`
- click Connect

## Hints

- Nothing is needed from your side in pom.xml or application.properties
- No new file need to be created.
- No new field need to be added, No field need to be removed or modified.
- If you will try to run testcases without defining relations, all Testcases will fail.

