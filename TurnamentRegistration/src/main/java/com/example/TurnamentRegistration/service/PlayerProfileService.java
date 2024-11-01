package com.example.TurnamentRegistration.service;


import com.example.TurnamentRegistration.entity.PlayerProfile;
import com.example.TurnamentRegistration.repository.PlayerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerProfileService {

    @Autowired
    PlayerProfileRepository repoProfile;

    public List<PlayerProfile> allPlayerProfiles(){
        return repoProfile.findAll();
    }

    public PlayerProfile getPlayerProfile(int id){
        Optional<PlayerProfile> tempProfile = repoProfile.findById(id);

        PlayerProfile pp = null;

        if(tempProfile.isEmpty()){
            throw new RuntimeException("PlayerProfile with id {"+ id +"} not found");
        } else {
            pp = tempProfile.get();
        }

        return pp;
    }


    public PlayerProfile addPlayerProfile(PlayerProfile profile){
        profile.setId(0);

        //check if profile contains nested player
        if(profile.getPlayer() != null){
            profile.getPlayer().setPlayerProfile(profile);
        }
        return repoProfile.save(profile);
    }

    /*
    public void deletePlayerProfile(int id){
        repoProfile.deleteById(id);
    }
    */
    public void deletePlayerProfile(int id) {
        PlayerProfile tempPlayerProfile = repoProfile.findById(id).get();

        //set the playerProfile field of the Player object to null
        tempPlayerProfile.getPlayer().setPlayerProfile(null);

        //set the player field of the PlayerProfile object to null
        tempPlayerProfile.setPlayer(null);

        //save changes
        repoProfile.save(tempPlayerProfile);

        //delete the PlayerProfile object
        repoProfile.delete(tempPlayerProfile);
    }

}
