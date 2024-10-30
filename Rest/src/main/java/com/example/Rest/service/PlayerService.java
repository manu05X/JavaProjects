package com.example.Rest.service;

import com.example.Rest.entity.Player;
import com.example.Rest.exception.PlayerNotFoundException;
import com.example.Rest.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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
            throw new PlayerNotFoundException("Player with id " + id + "not found. ");
        }

        return p;
    }

    //Add a player
    public Player addPlayer(Player p){
        //call repository method to add a player object to the player table
        return repo.save(p);
    }

    /*
    //Update a player
    public Player updatePlayer(int id, Player p){
        //get player Object by ID
        Player player = repo.getOne(id);

        //update player details
        player.setName(p.getName());
        player.setNationality(p.getNationality());
        player.setBirthDate(p.getBirthDate());
        player.setTitles(p.getTitles());

        //save update
        return repo.save(player);
    }
    */

    //Update a player
    public Player updatePlayer(int id, Player p) {
        Optional<Player> tempPlayer = repo.findById(id);

        if(tempPlayer.isEmpty())
            throw new PlayerNotFoundException("Player with id {"+ id +"} not found");

        p.setId(id);
        return repo.save(p);
    }

    //Partial update
    public Player patch(int id, Map<String, Object> partialPlayer){
        Optional<Player> player = repo.findById(id);

        if(player.isPresent()){
            partialPlayer.forEach((key, value) -> {
                System.out.println("Key: " + key + " Value: " + value);
                Field field = ReflectionUtils.findField(Player.class, key);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, player.get(), value);
            });
        }
        else {
            throw new PlayerNotFoundException("Player with id {"+ id +"} not found");
        }

        return repo.save(player.get());
    }

    //update a single field her titles
    @Transactional
    public void updateTitles(int id, int titles){
        Optional<Player> tempPlayer = repo.findById(id);

        if(tempPlayer.isEmpty()){
            throw new PlayerNotFoundException("Player with id {" + id + "} not found");
        }

        repo.updateTitles(id, titles);
    }


    //delete a player
    /*
    public String deletePlayer(int id){
        try{
            repo.deleteById(id);
        } catch (Exception e){
            return "Player with id " + id + "not found";
        }

        return "Deleted player with id" + id;
    }
    */

    //delete a player
    public void deletePlayer(int id) {
        Optional<Player> tempPlayer = repo.findById(id);

        if(tempPlayer.isEmpty())
            throw new PlayerNotFoundException("Player with id {"+ id +"} not found");

        repo.delete(tempPlayer.get());
    }

}

/*
- Remember, that we have used the JpaRepository interface for our PlayerRepository which provides all the basic methods for CRUD operations.
- This means that there is no need to write any implementation for the methods.
- We can simply call methods provided by the JpaRepository from the service layer.

- The JpaRepository provides the findAll() method that returns a List of objects.

- To retrieve an entity based on the id, JpaRepository provides the findById() method. This method has a return type of Optional. Optional is a design pattern introduced in Java 8, where instead of writing code to check for null values, we can see if a value is present in the Optional.

* */