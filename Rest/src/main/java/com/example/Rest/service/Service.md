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