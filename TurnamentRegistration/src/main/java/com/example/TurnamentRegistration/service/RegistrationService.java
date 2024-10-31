package com.example.TurnamentRegistration.service;

import com.example.TurnamentRegistration.entity.Registration;
import com.example.TurnamentRegistration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository repo;

    public List<Registration> getAllRegistrations(){
        return repo.findAll();
    }

    public Registration getRegistration(int id){
        Optional<Registration> temp = repo.findById(id);

        Registration r = null;

        if(temp.isEmpty()){
            throw new RuntimeException("No Registration with id : {" + id +"} ");
        } else {
            r = temp.get();
        }

        return r;
    }

    public Registration addRegistration(Registration registration){
        registration.setId(0);

        return repo.save(registration);
    }

    public void deleteRegistration(int id){
        Optional<Registration> temp = repo.findById(id);

        Registration r = null;

        if(temp.isEmpty()){
            throw new RuntimeException("No Registration with id : {" + id +"} ");
        } else {
            r = temp.get();
        }

        repo.delete(r);
    }
}
