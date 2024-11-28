# Achieve Dependency Injection using @Qualifier annotation

## Your project requires use of KeyVault and you need to add functionality in both AzureKeyVault and GoogleKeyVault so that they can be used in respective controllers

## Requirements
If you don't know about Spring @Qualifier Annotation, please google it once to get more understanding.

You need to complete saveSecret and retrieveSecret functions in both KeyVaults and use these keyvault implementations in respective controllers to achieve Dependency Injection using @Qualifier

1. In AzureKeyVault, use TreeMap to internally save and retrieve secrets.
2. In GoogleKeyVault, use HashMap to internally save and retrieve secrets.
3. Annotate Both GoogleKeyVault and AzureKeyVault with correct spring annotation so that Spring will be able to create bean(singleton object) for them.
4. We have already added IKeyVault as dependency in controllers, You just need to make sure that AzureKeyVault gets resolved in AzureController and GoogleKeyVault in GoogleController with use of Spring @Qualifier annotation.

## Hints
1. Use put() and get() functions of TreeMap and HashMap class for saving and retrieving secrets.
2. You need not to create any new file or any new method anywhere.
3. Don't remove IKeyVault from controllers and achieve Dependency Injection through interface only.
4. Strictly don't add concrete service implementations in controllers directly.
5. If you will try to run testcases without adding solution, all Testcases will fail with multiple errors.
