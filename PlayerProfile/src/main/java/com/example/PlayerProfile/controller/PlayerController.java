package com.example.PlayerProfile.controller;

import com.example.PlayerProfile.entity.Player;
import com.example.PlayerProfile.entity.PlayerProfile;
import com.example.PlayerProfile.service.PlayerProfileService;
import com.example.PlayerProfile.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerProfileService playerProfileService;

    @GetMapping
    public List<Player> allPlayers(){
        return playerService.allPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable int id){
        return playerService.getPlayer(id);
    }

    @PostMapping
    public Player addPlayer(@RequestBody Player player){
        player.setId(0);
        return playerService.addPlayer(player);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id){
        playerService.deletePlayer(id);
    }

    @PutMapping("/{id}/profiles/{profile_id}")
    public Player assignDetail(@PathVariable int id, @PathVariable int profile_id) {
        PlayerProfile profile = playerProfileService.getPlayerProfile(profile_id);
        System.out.println(profile);
        return playerService.assignProfile(id, profile);
    }

}
