# Service
___


## @PostRequest
- We will begin by writing the service layer method to add a player. This method, **addPlayer** takes a Player object as parameter and returns the entity that has been added.

```java
public Player addPlayer(Player p) {
    //call repository method to add a player object to the player table
}
```
- `save() method` : The `JpaRepository` interface inherits a method from the `CrudRepository` called `save()`. 
- This method handles both inserts and updates. To distinguish between an INSERT and UPDATE operation, the `save()` method checks the `primary key` of the object that is being passed to it. 
- If the `primary key` is `empty` or `null`, an INSERT operation is performed, otherwise an UPDATE to an existing record is performed.
```java
public Player addPlayer(Player p) {
    return repo.save(p);
}
```

## @PutMapping
- To handle the update based on the player’s id, we will create a method called `updatePlayer()` in the `PlayerService` class.
- This method takes in two arguments: a player’s `Id` and a Player `object`. It returns the **updated** Player `object`.
- The primary key passed in the method will be used to `fetch the existing record` from the `database`. We will use the `getOne()` method.
- Once we get the old player record from the database in `player`, we need to modify it based on the information in the Player object `p`, received as argument.
- For each field, we will call the getter methods on `p` to fetch the new information. Then, we will overwrite the old information by calling setter methods on `player`.
```java
public Player updatePlayer(int id, Player p) {
		
  //get player object by ID
  Player player = repo.getOne(id);

  //update player details
  player.setName(p.getName());
  player.setNationality(p.getNationality());
  player.setBirthDate(p.getBirthDate());
  player.setTitles(p.getTitles());

  //save updates
  return repo.save(player);
}
```
- The last step is to call repository methods to save the updated information. `JpaRespository` inherits the `save()` method offered by the `CrudRepository`.
- This method handles both insert and update operations on the repository. 
- It works by checking the `primary key` of the object and performing an `update if the key is present`. 
- If the **primary key** is `zero` or `null`, a new record is inserted into the database.

## @PatchMapping
- The method `patch()` will have two arguments.
- The `first` is the `id of the player` on which the `patch` is to be applied. 
- The `second` argument is the `Map` containing the `key-value` pairs of the fields that will be `updated`. 
- The `key` (field name) is a `String` while the `value` is an `Object` as it can have different datatypes.
- Inside the method, we will use the `id` to **fetch** the existing `Player` object from the database using the `findById()` method of the `JpaRepository`. 
- This method loads the entity from the database unlike the `getOne()` method, which returns a proxy object without hitting the database. 
- The `findById()` returns an `Optional` and we need to check if a `Player` object is returned using the `isPresent()` method.

### Using reflection
- We will loop through the `Map`, find the field that will be updated, and then change the value of that field in the existing `Player` object that we retrieved from the database in the previous step. 
- The `Reflection API` is used to `examine` and `modify fields`, methods, and classes at `runtime`. 
- It allows `access` to the **private fields** of a class and can be used to `access` the fields `irrespective` of their **access modifiers**. 
- Spring provides the `ReflectionUtils` class for handling `reflection` and working with the `Reflection API`.

### Finding the desired field
- The **ReflectionUtils** class has a **findField** method to identify the field of an object using a `String` name. 
- The `findField()` method takes **two arguments**, the class having the `field` and the `name of the field` which in our case is contained in the variable `key`. This method will return a `Field object`.
- ```java
  Field field = ReflectionUtils.findField(Player.class, key);

### Making the field accessible
- To set a value for this field, we need to set the field’s accessible flag to true. 
- `ReflectionUtils` `setAccessible()` method, when called on a field, toggles its accessible flag. 
- We can also use another method called `makeAccessible()`. This method makes the given field accessible by calling the `setAccessible(true`) method if necessary.
- ```java 
  ReflectionUtils.makeAccessible(field);
  
###  Setting the updated value
- Lastly, we will call the `setField()` method and use the `value` from the `Map` to set the field in the player object.
- The `setField()` method takes three arguments:
  * The reference of the field.
  * The object in which the field is to be set. We have used the `get()` method on the `Optional` player object to retrieve it. 
  * The value to set
- ```java
  ReflectionUtils.setField(field, player.get(), value);
  
- In this way, using reflection, a field can be updated in an object. 
- Since we are passing the fields to be updated as a `Map`, we will use the above steps while iterating through the map of key-value pairs.
- This code will iterate through the Map and make desired changes in the player object. 
- At the end, we will call the save() method to update the player record. The complete code of the method is shown below:
```java
public Player patch( int id, Map<String, Object> playerPatch) {

  Optional<Player> player = repo.findById(id);

  if(player.isPresent()) {			
      playerPatch.forEach( (key, value) -> {
          Field field = ReflectionUtils.findField(Player.class, key);
          ReflectionUtils.makeAccessible(field);
          ReflectionUtils.setField(field, player.get(), value);
      });
  }
  return repo.save(player.get());				
}
```

## Queries for partial update
- A point to note here is that we have used the `save()` method for applying the patch. 
- This method updates **all the columns** in the `table`. 
- For `large objects` with a lot of `fields`, this can have a `performance impact`. 
- To avoid this, we can implement `queries for partial updates`. These queries can target frequently updated columns. 
- If we want to update only the **titles column** of the Player table, we can create an `updateTitles()` method implementing a query in the PlayerRepository. 
- This method takes two arguments, the `ID` of the player and the `title` count. The `@Query` annotation is used to implement an update query as follows:
```java
@Modifying
@Query("update Player p set p.titles = :titles where p.id = :id")
void updateTitles(@Param("id") int id, @Param("titles") int titles);
```

## @Transactional
- The service layer will implement the updateTitles method.
- The `@Transactional` annotation ensures that the database is left in a consistent state. 
- The `transaction` is either `committed` or `rolled back` in case of failure.

```java
	//update a single field
	@Transactional
	public void updateTitles(int id, int titles) {
		Optional<Player> tempPlayer = repo.findById(id);

		if(tempPlayer.isEmpty())
			throw new RuntimeException("Player with id {"+ id +"} not found");

		repo.updateTitles(id, titles);
	}
```