package com.example.TurnamentRegistration.service;

import com.example.TurnamentRegistration.entity.Player;
import com.example.TurnamentRegistration.entity.PlayerProfile;
import com.example.TurnamentRegistration.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository repoPlayer;

    public List<Player> allPlayers(){
        return repoPlayer.findAll();
    }

    public Player getPlayer(int id){
        Optional<Player> tempPlayer = repoPlayer.findById(id);

        Player p = null;

        if(tempPlayer.isEmpty()){
            throw new RuntimeException("Player with id {"+ id +"} not found");
        } else {
            p = tempPlayer.get();
        }

        return p;
    }

    public Player addPlayer(Player player){
        player.setId(0);
        return repoPlayer.save(player);
    }

    public void deletePlayer(int id){
        Optional<Player> tempPlayer = repoPlayer.findById(id);
        Player p = null;
        if(tempPlayer.isEmpty()){
            throw new RuntimeException("Player with id {"+ id +"} not found");
        } else {
            p = tempPlayer.get();
        }

        repoPlayer.delete(p);
    }

    /*
    //assign a profile to a player
    public Player assignProfile(int id, PlayerProfile profile) {
        Player player = repoPlayer.findById(id).get();
        player.setPlayerProfile(profile);
        return repoPlayer.save(player);
    }
    */
    //assign a profile to a player
    public Player assignProfile(int id, PlayerProfile profile) {
        Player player = repoPlayer.findById(id).get();
        player.setPlayerProfile(profile);
        //bidirectional
        player.getPlayerProfile().setPlayer(player);
        return repoPlayer.save(player);
    }
}
