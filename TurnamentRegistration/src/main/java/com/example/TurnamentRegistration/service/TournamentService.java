package com.example.TurnamentRegistration.service;

import com.example.TurnamentRegistration.entity.Tournament;
import com.example.TurnamentRegistration.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    TournamentRepository tournamentRepo;

    public List<Tournament> allTournaments(){
        return tournamentRepo.findAll();
    }

    public Tournament getTournament(int id){
        Optional<Tournament> tempTournament = tournamentRepo.findById(id);

        Tournament t = null;

        if(tempTournament.isEmpty()){
            throw new RuntimeException("The Tournament with id {"+ id +"} not found");
        } else {
            t = tempTournament.get();
        }

        return t;
    }

    //add tournament
    public Tournament addTournament(Tournament tournament){
        tournament.setId(0);

        return tournamentRepo.save(tournament);
    }

    public void deleteTournament(int id){
        Optional<Tournament> temp = tournamentRepo.findById(id);
        Tournament t = null;

        if(temp.isEmpty()){
            throw new RuntimeException("The Tournament with id {"+ id +"} not found");
        } else {
            t = temp.get();
        }

        tournamentRepo.delete(t);
    }
}
