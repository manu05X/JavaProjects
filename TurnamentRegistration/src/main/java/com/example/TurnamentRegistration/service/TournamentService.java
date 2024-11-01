package com.example.TurnamentRegistration.service;

import com.example.TurnamentRegistration.entity.Category;
import com.example.TurnamentRegistration.entity.Registration;
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

    //Assigning registration to tournament
    /*
        We need a PUT mapping in the TournamentController class to assign a registration to a tournament. First, we need to write the service layer method addRegistration in the TournamentService class as shown:
     */
    public Tournament addRegistration(int id, Registration registration) {
        Tournament tournament = tournamentRepo.findById(id).get();
        tournament.addRegistration(registration);
        return tournamentRepo.save(tournament);
    }

    //This method removeRegistration in the TournamentService class which breaks the association between a Tournament and a Registration object by utilizing the removeRegistration method created in Tournament.
    public Tournament removeRegistration(int id, Registration registration) {
        Tournament tournament = tournamentRepo.findById(id).get();
        tournament.removeRegistration(registration);
        return tournamentRepo.save(tournament);
    }


    public Tournament addCategory(int id, Category category) {
        Tournament tournament = tournamentRepo.findById(id).get();
        tournament.addCategory(category);
        return tournamentRepo.save(tournament);
    }

    public Tournament removeCategory(int id, Category category) {
        Tournament tournament = tournamentRepo.findById(id).get();
        tournament.removeCategory(category);
        return tournamentRepo.save(tournament);
    }
}
