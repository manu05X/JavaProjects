# One-to-One Unidirectional Relationship
___
To model a one-to-one relationship, consider the scenario where a player has a profile that stores his details. We have a player table which stores the basic information about the player like id and name and a player_profile table which stores the additional details like the social media accounts of the players. There is a one-to-one relationship between player and player-profile tables, and in this lesson, we will model it as a one-way/ unidirectional relationship.

## Creating entities
- Create a `Player` class and mark it as an entity using `@Entity` annotation. We will only create id and player name fields at the moment to keep this example simple.

- Since `Id` is the primary key, we will mark it with `@Id` annotation and let Hibernate generate values for this column using the `@GeneratedValue` annotation and a `GenerationType` of `IDENTITY`. Hibernate requires a default constructor. We will also create an overloaded constructor to create the object using fields, getters and setters for the fields, and a `toString()` method.
```java
@Getter
@Setter
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String name;
}
```
- Next, we will create the `PlayerProfile` class in the `one-to-one` package to hold miscellaneous information about a player and also mark it as an `entity`. For now, this class will only store the player’s Twitter account handle.
```java
@Getter
@Setter
@Entity
public class PlayerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String twitter;

    //constructor
    //getters and setters
    //toString method
}
```
- We will mark the primary key with `@Id` annotation and generate the constructors, getters, setters and `ToString()` method as we did for the `Player` class. The full code of the `PlayerProfile` class can be seen from the executable code widget.

## One-to-One relationship
- In SQL, we represent relationships using primary key and foreign key. Foreign keys are used to link tables together. A foreign key is a field in one table that refers to the primary key in another table. We will see how the same can be achieved using Hibernate (which is the default implementation of Spring Data JPA).

- For the example taken in this lesson, the `Player` class has a one-to-one relationship with the `PlayerProfile` class. To show this relationship, we will add a field `playerProfile` to the `Player` class and use the JPA annotation `@OneToOne` on this field. `@OneToOne` is a JPA annotation which maps a source entity to a target entity.
```java
@OneToOne
  private PlayerProfile playerProfile;
```
## Cascade property
- The `cascade` property ensures that changes made to a `Player` entity are reflected in the `PlayerProfile` entity. 
- The `PlayerProfile` entity does not have a meaning of its own, rather, it defines the `Player` entity. 
- If we delete a `Player` entity, the associated details should also be deleted. Cascading allows an operation on the `Player` entity to be propagated to the `PlayerProfile` entity.
```java
@Entity
public class Player {
    //...

    @OneToOne(cascade= CascadeType.ALL)
    private PlayerProfile playerProfile;

    //...
}
```
- We have set the `CascadeType` to `ALL`. This means that all JPA and Hibernate specific operations on the `Player` entity will be propagated to the `PlayerProfile` entity.
> - **Note**: The absence of the cascade property, results in the TransientPropertyValueException exception when Hibernate tries to save a Player object containing a nested PlayerProfile object.

## @JoinColumn
- In relationships, one side is the owning side. We use the `@JoinColumn` annotation on the owning side. 
- Here, the `Player` class is the owning side of the relationship. The `@JoinColumn` annotation specifies the name of the foreign key column in the player table. 
- We will call the column `profile_id`. If the name is not specified, then JPA names the column based on some rules.

In the `player_profile` table, the column that is being `referenced` is id. The name of the corresponding field in the `PlayerProfile` class is `Id`, which we specify as `referencedColumnName`.
```java
@Entity
public class Player {

    //...
    
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="profile_id", referencedColumnName="id")
    private PlayerProfile playerProfile;

    //...

}
```
- We have also added a getter and setter for the new field and updated the `ToString()` method.
![img.png](images/img.png)
- After adding the @OneToOne annotation, if we run the application and check the H2 database, we can see that the player table has changed. It now contains a profile_Id column, which references the id column in the player_profile table. The player table is called the owning table because here we store the foreign key.
![img.png](images/img1.png)
- It is a unidirectional relationship because we have the reference of the `PlayerProfile` entity in the `Player` entity but we don’t have any reference of the `Player` entity in the `PlayerProfile` entity. We can retrieve the `PlayerProfile` object using the `Player` object but not the other way round.
![img.png](images/img2.png)

## Creating repositories
Next, we will create repositories for both classes, `Player` and `PlayerProfile`, that extend the `JpaRepository` interface. Since `JpaRepository` is a generic type, we need to specify the type of object as well as the datatype of the primary key.

We will create two interfaces named `PlayerRepository` and `PlayerProfileRepository` and annotate them with `@Repository` to leverage the exception translation facility offered by Spring.
```java
@Repository
public interface PlayerRepository extends JpaRepository <Player, Integer> {
}

@Repository
public interface PlayerProfileRepository extends JpaRepository <PlayerProfile, Integer> {
}
```
