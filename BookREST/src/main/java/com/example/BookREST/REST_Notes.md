# Introduction to Spring Boot REST API
___

## How to create a RESTful web service in Spring Boot?
- Creating a RESTful web service in Spring Boot involves several key steps. Below is a guide to help you build a simple RESTful web service using Spring Boot.

### A. Set Up Your Spring Boot Project
   Using Spring Initializr
1. Go to Spring Initializr.
2. Select the following options:
   - Project: Maven Project
   - Language: Java
   - Spring Boot: Latest stable version
   - Project Metadata: 
     - `Group` : com.example, 
     - `Artifact` : BookREST, 
     - `Name` : BookREST, 
     - `Description` : Demo project for Spring Boot REST, 
     - `Package name` : com.example.BookREST, 
     - `Pakaging`:  Jar,
   - Dependencies To Add: 
     - `Spring Web` : Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
     - `Lombok` : Java annotation library which helps to reduce boilerplate code., 
     - `Spring Boot DevTools` : Provides fast application restarts, LiveReload, and configurations for enhanced development experience., 
     - `Spring Web` : Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container., 
     - `Spring Data JPA` : Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate., 
     - `MySQL Driver` : MySQL JDBC driver..
3. Click "`Generate`" to download the project as a `ZIP file`.
4. Extract the ZIP file and open the project in your favorite IDE (e.g., IntelliJ IDEA, Eclipse).

### Stepwise Folder Creation (with DAO):
1. **Model (Entity) Folder:**
- Contains the classes that represent your application's data.
- **Folder Name**:` com.example.projectname.model`

2. **Repository Folder:**
- Contains repository interfaces that interact with the database, usually using Spring Data JPA or any ORM framework. 
- **Folder Name**: `com.example.projectname.repository`

3. **DAO Folder:**
- Contains DAO classes or interfaces that define low-level data access operations. These are more flexible than repositories and can use custom SQL queries.
- **Folder Name**: `com.example.projectname.dao`

4. **Service Folder:**
- Contains service classes where business logic is implemented. The service layer interacts with the DAO or repository layer to perform operations.
- **Folder Name**: `com.example.projectname.service`

5. **Controller Folder:**
- Contains controllers that handle incoming HTTP requests, interact with the service layer, and return responses.
- **Folder Name:** `com.example.projectname.controller`
