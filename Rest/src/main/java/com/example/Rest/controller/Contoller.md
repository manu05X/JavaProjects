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

```json
GET : ocalhost:8080/players
Output :
[
    {
    "id": 1,
    "name": "Djokovic",
    "nationality": "Serbia",
    "birthDate": "21-05-1987",
    "titles": 81
    },
    {
    "id": 2,
    "name": "Nadal",
    "nationality": "Spain",
    "birthDate": "03-06-1986",
    "titles": 88
    },
    {
    "id": 3,
    "name": "Isner",
    "nationality": "USA",
    "birthDate": "25-04-1985",
    "titles": 15
    }
]
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

```json
GET : ocalhost:8080/players/2
Output :
[
    {
    "id": 1,
    "name": "Djokovic",
    "nationality": "Serbia",
    "birthDate": "21-05-1987",
    "titles": 81
    },
    {
    "id": 2,
    "name": "Nadal",
    "nationality": "Spain",
    "birthDate": "03-06-1986",
    "titles": 88
    },
    {
    "id": 3,
    "name": "Isner",
    "nationality": "USA",
    "birthDate": "25-04-1985",
    "titles": 15
    }
]

OUTPUT :
        
{
"id": 2,
"name": "Nadal",
"nationality": "Spain",
"birthDate": "03-06-1986",
"titles": 88
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
```json
POST : localhost:8080/players
{
"name": "Federer",
"nationality": "Switzerland",
"birthDate": "22-11-1984",
"titles": 151
}

output that is saved in DB
{
"id": 4,
"name": "Federer",
"nationality": "Switzerland",
"birthDate": "22-11-1984",
"titles": 151
}
```
- Inside the `addPlayer()` method, we will set the primary key to zero. 
- This is done to ensure that if the client accidentally passes the id of a player to be added, we remove it from the request before delegating the call to the service layer. 
- The `save()` method offered by the `JpaRepository` works for both INSERT and UPDATE requests by checking the primary key and performs an INSERT or UPDATE operation depending upon its value.


## @PutMapping("/players/{id}")
- The HTTP PUT request is used for updates. The **REST client** will send a PUT request to `/players/{playerId}` with JSON data containing the information to be updated. The player’s ID is a path variable.
- The **REST service** will convert the JSON data to a `Player object`, and using the id of the player; it will `overwrite the player’s` record in the database with the one sent in the `PUT request`. 
- On success, the REST service will respond with the `updated player` record (which is an echo of the request).
- The `@PutMapping` is a shortcut annotation for mapping a `PUT request` to a controller method. It is the same as: `@RequestMapping(method=RequestMethod.PUT)`

```java
@PutMapping("/players/{id}")
public Player updatePlayer(@PathVariable int id, @RequestBody Player player) {
 
}
```
- We will send the `Id` of the player to be updated as a path variable (`@PathVariable`). 
- The request body (`@RequestBody`) contains the JSON with the **updated** information of the player. 
- The method will return a **Player object** which will be converted to JSON and sent back to the client.
```java
@PutMapping("/players/{id}")
public Player updatePlayer(@PathVariable int id, @RequestBody Player player) {
    return service.updatePlayer(id, player);
}

```
- Click the “Send” button to send a PUT request and view the response in the “RESPONSE” tab. The response is an echo of the request and indicates that the request has been successful. The player with id 2 is now Nadal.
- Suppose we want to update the information of the player with id `2` so the URL is `/players/2`. 
- We will provide the data for a new player, Nadal. To send the JSON in the request, choose "Body", then choose "raw" and from the dropdown choose "JSON".
```json
PUT : localhost:8080/players/2
        
{
    "name" : "Nadal",
    "nationality": "Spain",
    "birthDate" : "03-06-1986",
    "titles" : 88
}


OUTPUT :
        
{
"id": 2,
"name": "Nadal",
"nationality": "Spain",
"birthDate": "03-06-1986",
"titles": 88
}

```
## @PatchMapping
- The PUT method updates the whole record. There may be a scenario when only one or two fields needs to be updated. 
- In that case, sending the whole record does not make sense. The HTTP PATCH method is used for partial updates.
- Sometimes we may need to update a single field. For example, once we enter a player in our database, the field that will most likely change is his titles count. 
- The player entity only has a few fields and PUT can be used for update. 
- But if the entity is large and contains nested objects, it will have a performance impact to send the whole entity only to update a single field.
- Partial request means that we only send the `titles` in the request body instead of the whole Player object. 
- If we use PUT to send a partial request, all other fields are set to null.
- If a `PUT` request with the following request body is sent to `/players/1`:
```java
{
    "titles": 161
}

PUT : https://localhost:8080/players/1

OUTPUT :
        
        {
            "id": 1,
            "name": null,
            "nationality": null,
            "birthDate": null,
            "titles": 161
        }
        
```
- The `titles` have been `modified` but the rest of the values are now `null` which is `not the desired outcome`. 
- The `PUT` method requires the entire object to be sent, even when we want to `modify` a `single field`. 
- If partial data is sent, then all other fields are set to `null`. 
- `PATCH` comes in handy in such situations. It allows a list of changes to be applied to the entity.
- The method takes a `Map` argument containing the `key-value pair` of the fields we want to update. 
- Since the field names are `String` and the values can be any datatype, we will use `Map<String, Object>`. 
- The list of fields and their values will come in the request body and the `@RequestBody` annotation binds the JSON to the `Map` variable.

```java
@PatchMapping("/players/{id}")
public Player partialUpdate( @PathVariable int id, @RequestBody Map<String, Object> playerPatch) {
    // call service layer method for patch	
    return service.patch(id, playerPatch);	        
}
```

## @PatchMapping("/players/{id}/titles")
-  We can define a new PATCH mapping for `/players/{id}/titles ` `id` is the path variable.
- This method accepts an integer from the request body.

```java
	@PatchMapping("/players/{id}/titles")
	public void updateTitles(@PathVariable int id, @RequestBody int titles) {
		service.updateTitles(id, titles);	        
	}

PATCH : localhost:8080/players/2/titles
- The Body Raw input 

120

INPUT : 
120

OUTPUT : The REST API responds with 200 status code which indicates that the request was successful.
 
1
```

## @DeleteMapping
- We will create a method deletePlayer to handle the `DELETE` request. It will map to the endpoint `/players/{id}`, with id being the path variable. 
- The `REST` controller will respond with a message informing the client of the success or failure. The method is annotated with `@DeteleMapping`. 
- The `@PathVariable` annotation binds the id path variable to the method parameter id.

```java

    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable int id){
        serviceObj.deletePlayer(id);
    }
```