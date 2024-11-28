# Demonstrate usage of @Primary to give higher preference to a Bean when multiple beans of same type are available

## Your project is using 2 Types of NotificationService to send notification, which are EmailNotificationService and SmsNotificationService, You need to make add implementation in EmailNotificationService and make it Primary

## Your project is also using Multiple Beans of Content which vary based on ContentType and Message. You need to define a Content Bean with Content Type - TEXT and make it Primary Bean

## Requirements
1. If you don't know about @Primary , please google it once to understand more.
2. You are given SmsNotificationService and EmailNotificationService both implementing INotificationService, You need to complete sendNotification() method in EmailNotificationService taking help from implementation already present in SmsNotificationService. Please change NotificationType to EMAIL in this implementation.
3. Please declare EmailNotificationService as Primary.
4. In NotificationConfig File, you will find 3 definitions for creating bean of Content with different Content Types. Please add more definition for creating bean of Content with ContentType.TEXT taking help from other implementations. Also make this Content definition with ContentType.TEXT as Primary.

## Hints
1. You don't need to create any new file.
2. Please don't change any dependency in any file.
3. If you will try to run testcases without adding solution, all Testcases will fail with multiple errors which may be incorrect as well.
4. You need to use relevant Spring annotations at relevant places like @Primary, @Bean etc
5. Please take a look into ContentType and NotificationType classes to see what all values are possible.
