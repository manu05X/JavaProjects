package com.example.TurnamentRegistration.controller;

import com.example.TurnamentRegistration.entity.Player;
import com.example.TurnamentRegistration.entity.PlayerProfile;
import com.example.TurnamentRegistration.entity.Registration;
import com.example.TurnamentRegistration.service.PlayerProfileService;
import com.example.TurnamentRegistration.service.PlayerService;
import com.example.TurnamentRegistration.service.RegistrationService;
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

    @Autowired
    RegistrationService registrationService;

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

    @PutMapping("/{id}/registrations/{registration_id}")
    public Player assignRegistration(@PathVariable int id, @PathVariable int registration_id){
        Registration registration = registrationService.getRegistration(registration_id);
        System.out.println(registration);
        return playerService.assignRegistration(id, registration);
    }

    @PutMapping("/{id}/remove_registrations/{registration_id}")
    public Player removeRegistration(@PathVariable int id, @PathVariable int registration_id){
        Registration registration = registrationService.getRegistration(registration_id);
        System.out.println(registration);
        return playerService.removeRegistration(id, registration);
    }

}
