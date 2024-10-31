package com.example.TurnamentRegistration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String location;

    private List<Registration> registrations = new ArrayList<>();

    //update constructor, generate getter and setter methods , update toString()
    public Tournament(String name, String location, List<Registration> registrations){
        super();
        this.name = name;
        this.location = location;
        this.registrations = registrations;
    }


    public List<Registration> getRegistrations(){
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations){
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Tournament [id=" + id + ", name=" + name + ", location=" + location + ", registrations=" + registrations
                + "]";
    }

}
