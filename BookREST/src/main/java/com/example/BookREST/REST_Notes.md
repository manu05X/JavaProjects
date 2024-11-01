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

```java
src/
        └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           └── BookREST/
        │               ├── controller/
        │               │   └── BookController.java          # Handles incoming HTTP requests
        │               ├── dao/
        │               │   └── BookDAO.java                 # DAO layer for data access operations
        │               ├── dto/
        │               │   └── BookDTO.java                 # Data Transfer Object (Optional)
        │               ├── mapper/
        │               │   └── BookMapper.java              # Converts between Entity and DTO
        │               ├── model/
        │               │   └── Book.java                    # Entity representing Book
        │               ├── repository/
        │               │   └── BookRepository.java          # Repository extending JpaRepository
        │               └── services/
        │                   └── BookServices.java            # Service layer for business logic
        └── resources/
            └── application.properties                       # Configuration file (e.g., for DB connection)

```

### Explanation:
 - `controller/:` Contains the BookController.java, responsible for handling HTTP requests and delegating business logic to the service layer.
 - `dao/:` Contains the BookDAO.java, which provides an abstraction for accessing the database via the repository.
 - `dto/:` Contains the BookDTO.java (optional), which represents a simplified version of the Book entity, often used for data transfer between layers.
 - `mapper/:` Contains BookMapper.java, which maps between Book entities and BookDTO (optional if DTO is used).
 - `model/:` Contains the Book.java, which is the entity class representing the data model.
 - `repository/:` Contains BookRepository.java, which extends JpaRepository to handle CRUD operations for Book entities.
 - `services/:` Contains BookServices.java, which implements business logic and interacts with the DAO layer.
 - `resources/:` Contains application.properties for configuring database connections and other properties.


### Test the RESTful Web Service
You can test the endpoints using tools like Postman or curl:
 - Get all books: **GET** http://localhost:8080/books
 - Get book by ID: **GET** http://localhost:8080/books/{id}
 - Create a new book: **POST** http://localhost:8080/books with a JSON body
 - Update a book: **PUT** http://localhost:8080/books/{id} with a JSON body
 - Delete a book: **DELETE** http://localhost:8080/books/{id}

### Example JSON for Creating/Updating a Book
```json
    {
        "title": "Arthashastra",
        "author": "Kautilya"
    },
    {
        "title": "Macbeth",
        "author": "William Shakespeare"
    },
    {
        "title": "The Namesake",
        "author": "Jhumpa Lahiri"
    },
    {
        "title": "Odyssey",
        "author": "Homer"
    }
```

___

1. **Controller**: Handles incoming HTTP requests and directs them to the appropriate service methods.
2. **Service**: Contains the business logic and interacts with the DAO for data operations.
3. **DAO**: Abstracts data access and communicates with the repository to perform CRUD operations.
4. **Repository**: Interface that interacts directly with the database and handles entity persistence.
5. **Entity**: Represents the database table and is mapped to it; this is the domain model.
6. **DTO**: Data Transfer Object used to encapsulate data to be sent over the network, typically used to simplify the representation of the entity.
7. **Mapper**: Converts between the Entity and DTO, ensuring the proper transformation of data when transferring between layers.

___
In a typical layered architecture, the Data Transfer Object (DTO) and Data Access Object (DAO) serve distinct purposes and are positioned between specific layers. Here’s how they fit in:

### Layer Positioning
1. **Controller Layer**:
- Interacts with: Service Layer
- Purpose: Receives HTTP requests, processes input, and returns responses.

2. **Service Layer**:
 - Interacts with:
   - DAO
   - DTO
 - **Purpose**: Contains business logic and orchestrates data retrieval or manipulation by interacting with the DAO. The Service layer uses DTOs to send and receive data.

3. DAO Layer:
 - Interacts with: Repository Layer
 - Purpose: Abstracts the data access layer by providing an interface for CRUD operations. It acts as an intermediary between the Service layer and the Repository layer.

4. Repository Layer:
 - Interacts with: Entity Layer
 - Purpose: Handles direct database operations and is typically a JPA interface or similar.

5. Entity Layer:
 - Purpose: Represents the data model corresponding to database tables.

6. DTO:
 - Interacts with: Service Layer
 - Purpose: Represents the data structure for transferring data between the Service layer and the Controller layer. DTOs often simplify the data structure by only including the fields that are necessary for a particular operation.

**Summary**
 - DTO sits between the Service Layer and the Controller Layer.
 - DAO sits between the Service Layer and the Repository Layer.

**Layer Flow**
1. **Controller Layer** → **Service Layer** → **DAO** → **Repository Layer** → **Entity Layer**
2. **Service Layer** ↔ **DTO** (for data transfer to/from the **Controller**)

This positioning ensures a clear separation of concerns and facilitates maintainability and testability in your application architecture.

### Role of DTO in the Architecture
1. **Controller Layer:**

 - Receives HTTP requests from clients.
 - Uses DTOs to encapsulate the data needed for the request or response.
 - Converts incoming request data (from the client) into DTOs to pass to the Service Layer.

2. **Service Layer:**

 - Contains the business logic of the application.
 - Receives DTOs from the Controller Layer, processes the data, and may transform it into entities for data persistence.
 - Returns DTOs to the Controller Layer, which may contain processed or updated data that will be sent back to the client.

### Example Flow
1. A client sends a request to the Controller Layer. 
2. The Controller Layer maps the request data to a DTO. 
3. The Controller Layer calls the corresponding method in the Service Layer, passing the DTO. 
4. The Service Layer processes the data and may call the DAO to interact with the Repository Layer. 
5. After processing, the Service Layer returns a DTO back to the Controller Layer. 
6. The Controller Layer converts the DTO into an HTTP response to send back to the client.

**Summary**

Using **DTOs** helps to simplify data handling between the layers, allows for better control over the data being transferred, and helps maintain separation of concerns within the application.



### Explain the use of @RestController? and what is Difference between @Controller vs @RestController?
#### Understanding @RestController in Spring Boot
#### @RestController
- `@RestController` is a convenience annotation in Spring Boot that **combines** `@Controller` and `@ResponseBody`. It is used to create `RESTful` web services. The primary function of `@RestController` is to simplify the creation of web services by eliminating the need to annotate each method with `@ResponseBody`.

**Key Points:**
- It marks the class as a **controller** where every method returns a domain object instead of a view.
- By default, it applies **@ResponseBody** to all the methods, meaning that the return values of these methods will be converted to **JSON** or **XML** (depending on the configuration) and sent directly in the **HTTP response**.

**@Controller vs. @RestController**

**@Controller**
- Used to mark a class as a Spring _MVC_ controller.
- Typically used in web applications where the controller’s methods return views (like JSP, Thymeleaf templates).
- Methods in a **@Controller** class are often used to handle HTTP requests and return model and view objects.
- To return **JSON** or **XML** data from a method, you need to annotate the method with **@ResponseBody**.