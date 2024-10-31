package com.example.TurnamentRegistration.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String location;

    // @OneToMany(cascade=CascadeType.REMOVE)
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="tournament_id")
    private List<Registration> registrations = new ArrayList<>();

    public Tournament() {
    }

    public Tournament(String name, String location) {
        super();
        this.name = name;
        this.location = location;
    }

    public Tournament(String name, String location, List<Registration> registrations) {
        super();
        this.name = name;
        this.location = location;
        this.registrations = registrations;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    //set up one to many relationship
    public void addRegistration(Registration reg) {
        registrations.add(reg);
    }

    //remove registration :  method removeRegistration in the Tournament class which removes a registration from the List of Registrations. It is called by removeRegistration from TournamentService.
    public void removeRegistration(Registration reg) {
        if (registrations != null)
            registrations.remove(reg);
    }

    @Override
    public String toString() {
        return "Tournament [id=" + id + ", name=" + name + ", location=" + location + ", registrations=" + registrations
                + "]";
    }
}