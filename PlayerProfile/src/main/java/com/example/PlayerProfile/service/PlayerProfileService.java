package com.example.PlayerProfile.service;

import com.example.PlayerProfile.entity.PlayerProfile;
import com.example.PlayerProfile.repository.PlayerProfileRepository;
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

        return repoProfile.save(profile);
    }


    public void deletePlayerProfile(int id){
        repoProfile.deleteById(id);
    }

}
