package com.example.TurnamentRegistration.entity;

import jakarta.persistence.*;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})//, fetch=FetchType.LAZY
    @JoinColumn(name="player_id", referencedColumnName = "id")
    private Player player;

    public Registration(){

    }



    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString(){
        return "Registration [id=" + id + ", player=" + player + "]";
    }
}
