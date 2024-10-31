package com.example.TurnamentRegistration.controller;

import com.example.TurnamentRegistration.entity.Registration;
import com.example.TurnamentRegistration.entity.Tournament;
import com.example.TurnamentRegistration.service.RegistrationService;
import com.example.TurnamentRegistration.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    @Autowired
    RegistrationService registrationService;

    @GetMapping
    public List<Tournament> allTournaments(){
        return tournamentService.allTournaments();
    }

    @GetMapping("/{id}")
    public Tournament getTournament(@PathVariable int id){
        return tournamentService.getTournament(id);
    }

    @PostMapping
    public Tournament addTournament(@RequestBody Tournament tournament){
        return tournamentService.addTournament(tournament);
    }

    @DeleteMapping("/{id}")
    public void deleteTournament(@PathVariable int id){
        tournamentService.deleteTournament(id);
    }

    @PutMapping("/{id}/registrations/{registration_id}")
    public Tournament addRegistration(@PathVariable int id, @PathVariable int registration_id) {
        Registration registration = registrationService.getRegistration(registration_id);
        System.out.println(registration);
        return tournamentService.addRegistration(id, registration);
    }

    //The removeRegistration method removes the registration entity having registraion_id as its key from the Tournament entity specified using id.
    @PutMapping("/{id}/remove_registrations/{registration_id}")
    public Tournament removeRegistration(@PathVariable int id, @PathVariable int registration_id) {
        Registration registration = registrationService.getRegistration(registration_id);
        System.out.println(registration);
        return tournamentService.removeRegistration(id, registration);
    }
}
