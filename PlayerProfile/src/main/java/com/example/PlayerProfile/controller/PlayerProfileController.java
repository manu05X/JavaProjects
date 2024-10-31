package com.example.PlayerProfile.controller;

import com.example.PlayerProfile.entity.PlayerProfile;
import com.example.PlayerProfile.service.PlayerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerProfileController {

    @Autowired
    PlayerProfileService service;

    @GetMapping("/profiles")
    public List<PlayerProfile> allPlayerProfiles(){
        return service.allPlayerProfiles();
    }

    @GetMapping("/profiles/{id}")
    public PlayerProfile getPlayerProfile(@PathVariable int id){
        return service.getPlayerProfile(id);
    }

    @PostMapping("/profiles")
    public PlayerProfile addPlayerProfile(@RequestBody PlayerProfile playerProfile){
        return service.addPlayerProfile(playerProfile);
    }

    @DeleteMapping("/profiles/{id}")
    public void deletePlayerProfile(@PathVariable int id){
        service.deletePlayerProfile(id);
    }
}
