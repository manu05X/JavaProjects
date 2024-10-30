# Repository
___


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
- The query must be used with the `@Modifying `annotation to execute the `UPDATE` query. 
- The `@Param` annotation binds the method parameters to the query. 
- This method will only change a single column of the table unlike the `save()` method which updates all the columns of the table.