# Defining relation between different Purchase Options

## Requirements

1. You are provided with 4 models - `PurchaseDetails`, `BankAccount`, `CreditCard` and `PayLater`
2. Fields are already present in these models, you need not to add/modify/remove any field.
3. You need to make sure that Tables are created for only concrete payment methods. 
4. Also make sure that Column created for credit card owner is `CREDIT_CARD_OWNER` and Column created for bank account owner is `ACCOUNT_HOLDER`

## Testing

You can also check which tables with what fields are created in H2 by running Application in IntellIJ and opening  `http://localhost:8080/h2-console` on browser and put values as below
- Saved Settings: `Generic H2(Embedded)`
- Setting Name: `Generic H2(Embedded)`
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:class10_ques4`
- User Name: `sa`
- Password: `password`
- click Connect

## Hints

- Nothing is needed from your side in pom.xml or application.properties
- No new file need to be created.
- No new field need to be added, No field need to be removed or modified.
- If you will try to run testcases without defining relations, all Testcases will fail.