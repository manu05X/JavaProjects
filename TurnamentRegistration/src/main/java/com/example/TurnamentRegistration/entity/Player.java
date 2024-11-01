package com.example.TurnamentRegistration.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.*;


@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(cascade=CascadeType.ALL)//, optional = false)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    // @JsonManagedReference
    private PlayerProfile playerProfile;

    @OneToMany(mappedBy="player", cascade= CascadeType.ALL)
    private List<Registration> registrations = new ArrayList<>();


    public Player( ) {

    }

    public Player(String name) {
        super();
        this.name = name;
    }

    public Player(String name, PlayerProfile profile) {
        super();
        this.name = name;
        this.playerProfile = profile;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public PlayerProfile getPlayerProfile() {

        return playerProfile;
    }

    public void setPlayerProfile(PlayerProfile playerProfile) {

        this.playerProfile = playerProfile;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    //set up bi-directional relationship with Registration class
    public void registerPlayer(Registration reg) {
        //add registration to the list
        registrations.add(reg);
        //set the player field in the registration
        reg.setPlayer(this);
    }

    public void removeRegistration(Registration reg) {
        if (registrations != null)
            registrations.remove(reg);
        //set the player field in the registration
        reg.setPlayer(null);
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", playerProfile=" + playerProfile + ", registrations="
                + registrations + "]";
    }
}
