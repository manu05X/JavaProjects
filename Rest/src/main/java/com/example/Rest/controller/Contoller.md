# Service
___

## @GetMapping annotation
- The client sends an HTTP request to the REST service. The dispatcher servlet handles the request and if the request has JSON data, the **HttpMessageConverter** converts it to **Java objects**. 
- The request is `mapped` to a `controller` which calls **service layer methods**. 
- The service layer `delegates the call` to repository and **returns the data as POJO**. The **MessageConverter** `converts` the data to `JSON` and it is sent back to the client.
- The controller method `calls` the service layer method `getAllPlayers()`. 
- To be able to use the `PlayerService` methods, we will **autowire** (`@Autowire`) PlayerService in the PlayerController class.

```java

@RestController
public class PlayerController {

    @Autowired
    PlayerService service;

}
```
- To be able to use methods from the **PlayerRepository**, we have **autowired**(`@Autowire`) it in the `PlayerService class`.
```java
@Service
public class PlayerService {

    @Autowired
    PlayerRepository repo;

    //...
}
```


## @PathVariable annotation
- We will create a method getPlayer() for retrieving a player by id.
- The endpoint `/players/{id}` maps to this method. We will set up a @GetMapping for `/players/{id}`, where id in curly braces means that it is a path variable.
- Since there is a path variable in the endpoint, we need to bind it with a method parameter.
- The @PathVariable annotation binds the path variable {id} from the URL to the method parameter id. 
- By default, both the names must be the same for the binding to work.

```java
	@GetMapping("/players/{id}")
	public Player getPlayer(@PathVariable int id){
		return service.getPlayer(id);
	}
```


## @PostMapping annotation
- The REST client will send a POST request to `/players`. 
- The body of the request contains the` JSON data` for a **player**. 
- Since this is a `new player`, the client will not pass the ID (`primary key`). The backend system will `generate` the key for the **new record**.
- The `@PostMapping` annotation maps HTTP POST requests to controller methods. It is a shortcut annotation for: `@RequestMapping(method = RequestMethod.POST)`.
- We will create a method `addPlayer()` in the **PlayerController** class. 
- This method will have a mapping for a **POST** request to `/players`. The method will **return** the `inserted record` back to the **client**.
```java
@PostMapping("/players")
public Player addPlayer(@RequestBody Player player) {

}
```
- The client will send the player data in the **request body** as **JSON**. Jackson will `convert` the incoming `JSON data to POJO`. 
- The `@RequestBody` annotation handles this conversion and `binds the data` in the request body to a **method parameter**.
- The `@RequestBody` annotation binds the JSON from the request to the `Player object`. It converts JSON to POJO without us having to parse the request body. We can directly use the data in the player object now.

```java
@PostMapping("/players")
public Player addPlayer(@RequestBody Player player) {
    player.setId(0);
    return service.addPlayer(player);
}
```
- Inside the `addPlayer()` method, we will set the primary key to zero. 
- This is done to ensure that if the client accidentally passes the id of a player to be added, we remove it from the request before delegating the call to the service layer. 
- The `save()` method offered by the `JpaRepository` works for both INSERT and UPDATE requests by checking the primary key and performs an INSERT or UPDATE operation depending upon its value.


## @PutMapping
- The HTTP PUT request is used for updates. The **REST client** will send a PUT request to `/players/{playerId}` with JSON data containing the information to be updated. The player’s ID is a path variable.
- The **REST service** will convert the JSON data to a `Player object`, and using the id of the player; it will `overwrite the player’s` record in the database with the one sent in the `PUT request`. 
- On success, the REST service will respond with the `updated player` record (which is an echo of the request).