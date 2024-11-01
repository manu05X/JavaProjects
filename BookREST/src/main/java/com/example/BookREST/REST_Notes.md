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
___
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
___


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

**@RestController**
- Specialized version of @Controller used for `RESTful` web services.
- Combines the functionality of `@Controlle`r and `@ResponseBody`.
- Every method in a `@RestController` class automatically returns data (usually JSON or XML) directly in the HTTP response.
___
### How to handle HTTP methods (GET, POST, PUT, DELETE)?
Handling HTTP methods (GET, POST, PUT, DELETE) in a Spring Boot application involves defining methods in your controller class and annotating them appropriately. Below are examples of how to handle each of these HTTP methods using Spring Boot.

#### Explanation of HTTP Methods
1. **GET**:
   - @GetMapping is used to handle HTTP GET requests.
   - Example: Retrieve all books or a book by ID.

2. **POST**:
   - @PostMapping is used to handle HTTP POST requests.
   - Example: Create a new book.

3. **PUT**:
   - @PutMapping is used to handle HTTP PUT requests.
   - Example: Update an existing book.

4. **DELETE**:
   - @DeleteMapping is used to handle HTTP DELETE requests.
   - Example: Delete a book by ID.
___
### How to use @RequestMapping, @GetMapping, @PostMapping, etc.?
Certainly! In Spring Boot, @RequestMapping, @GetMapping, @PostMapping, etc., are annotations used to map HTTP requests to specific handler methods in your controller classes. Let's go through each of these annotations and see how they are used.

1. @RequestMapping
   - @RequestMapping is a general-purpose annotation used to map web requests to handler methods in Spring MVC and Spring Boot applications. It can be applied at the class level and/or method level.
```java
@RestController
@RequestMapping("/api/books")
public class BookController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable Long id) {
        // Implementation
    }
}
```
In this example:
- The class-level `@RequestMapping("/api/books")` annotation specifies that all requests to `/api/books` will be handled by this controller.
- The method-level `@RequestMapping(value = "/{id}", method = RequestMethod.GET)` annotation specifies that GET requests to `/api/books/{id}` will be handled by the getBookById() method.

2. @GetMapping
   - @GetMapping is a shortcut for @RequestMapping(method = RequestMethod.GET). It is used to handle HTTP GET requests.
   Example:
```java
@RestController
@RequestMapping("/api/books")
public class BookController {

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        // Implementation
    }
}
```
3. @PostMapping
   - @PostMapping is a shortcut for @RequestMapping(method = RequestMethod.POST). It is used to handle HTTP POST requests.
```java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        // Implementation
    }
}
```
4. @PutMapping
   - @PutMapping is a shortcut for @RequestMapping(method = RequestMethod.PUT). It is used to handle HTTP PUT requests.
```java
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        // Implementation
    }
}
```
5. @DeleteMapping
   - @DeleteMapping is a shortcut for @RequestMapping(method = RequestMethod.DELETE). It is used to handle HTTP DELETE requests.
```java
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        // Implementation
    }
}
```
**Summary**
- @RequestMapping is a generic annotation to map web requests to handler methods.
- @GetMapping, @PostMapping, @PutMapping, and @DeleteMapping are shortcuts for specific HTTP methods.
- These annotations can be applied at both class and method levels to handle requests at specific endpoints.

By using these annotations, you can easily define the mappings between HTTP requests and your handler methods in Spring Boot applications.
___

### Explain the use of @PathVariable and @RequestParam.
Sure! Both @PathVariable and @RequestParam are annotations used in Spring MVC and Spring Boot to handle parameters in web requests. However, they serve different purposes and are used in different scenarios.

1. **@PathVariable**
   - @PathVariable is used to extract values from the URI (or URL) template of the request. It is typically used to capture values from the URI and use them in your controller methods.
   Example:
```java
@RestController
@RequestMapping("/api/books")
public class BookController {

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        // Implementation to fetch book by ID
    }
}
```
In this example, {id} in the URI template /api/books/{id} is captured by @PathVariable Long id. When a request is made to /api/books/123, the value 123 is extracted and passed to the getBookById() method.

2. **@RequestParam**
   - @RequestParam is used to extract query parameters from the request URL. Query parameters are the key-value pairs that appear after the ? in a URL.
   Example:
```java
@RestController
@RequestMapping("/api/books")
public class BookController {

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String title) {
        // Implementation to search books by title
    }
}
```
In this example, @RequestParam String title extracts the value of the title query parameter from the URL /api/books/search?title=Spring. When a request is made with /api/books/search?title=Spring, the value "Spring" is extracted and passed to the searchBooks() method.


**Key Differences**
**Path Variables (@PathVariable):**
 - Used to extract values from the URI template.
 - Typically used for required parameters in the URI.
 - Values are part of the URI path.

**Request Parameters (@RequestParam):**
- Used to extract query parameters from the request URL.
- Can be used for both required and optional parameters.
- Values are part of the query string in the URL.

**Summary**
- Use @PathVariable to extract values from the URI template.
- Use @RequestParam to extract query parameters from the request URL.
- Both annotations help in handling different types of parameters in web requests efficiently in Spring Boot applications.
___









