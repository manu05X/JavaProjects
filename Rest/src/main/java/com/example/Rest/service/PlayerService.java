package com.example.Rest.service;

import com.example.Rest.entity.Player;
import com.example.Rest.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    //To be able to use methods from the PlayerRepository, we have autowired it in the PlayerService class.
    @Autowired
    PlayerRepository repo;

    // This method will call a repository method and return a list of objects.
    public List<Player> getAllPlayers(){
        //call repository method
        return repo.findAll();
    }

    //Get player by ID
    public Player getPlayer(int id){
        Optional<Player> tempPlayer = repo.findById(id);

        Player p = null;

        //if the Optional has a value, assign it to p
        if(tempPlayer.isPresent()){
            p = tempPlayer.get();
        }
        //if value is not found, throw a runtime exception
        else{
            throw new RuntimeException("Player with id " + id + "not found. ");
        }

        return p;
    }

    //Add a player

    //Update a player

    //Partial update

    //delete a player
}

/*
- Remember, that we have used the JpaRepository interface for our PlayerRepository which provides all the basic methods for CRUD operations.
- This means that there is no need to write any implementation for the methods.
- We can simply call methods provided by the JpaRepository from the service layer.

- The JpaRepository provides the findAll() method that returns a List of objects.

- To retrieve an entity based on the id, JpaRepository provides the findById() method. This method has a return type of Optional. Optional is a design pattern introduced in Java 8, where instead of writing code to check for null values, we can see if a value is present in the Optional.

* */