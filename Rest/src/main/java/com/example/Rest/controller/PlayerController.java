package com.example.Rest.controller;

import com.example.Rest.entity.Player;
import com.example.Rest.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    PlayerService serviceObj;

    @GetMapping("/welcome")
    public String welcome(){
        return "Tennis Player REST API";
    }

    @GetMapping("/players")
    public List<Player> allPlayers(){
        return serviceObj.getAllPlayers();
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable int id){
        return serviceObj.getPlayer(id);
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player){
        player.setId(0);
        return serviceObj.addPlayer(player);
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody Player player){
        return serviceObj.updatePlayer(id, player);
    }


}
/*


- The getAllPlayers method returns a List of Player objects. The REST API will convert that list of POJOs to JSON and return it to the client.
- The controller method calls the service layer method getAllPlayers().
- To be able to use the PlayerService methods, we will autowire PlayerService in the PlayerController class.
- The getAllPlayers method returns a List of Player objects. The REST API will convert that list of POJOs to JSON and return it to the client.


- Path variables are a way of parameterizing the path or endpoint to accept data.
- Path variables are written in curly braces. When the client sends a request, it passes a value in place of the path variable.
- For example, we could say /players/1 to give us the player with Id 1, or /players/3 for the player with Id 3.

- JpaRepository interface provides us with methods for all basic CRUD operations. We need to write a service method to call the repository.
- We will call this method getPlayer. It takes an integer id as input and returns a Player object.


 */
