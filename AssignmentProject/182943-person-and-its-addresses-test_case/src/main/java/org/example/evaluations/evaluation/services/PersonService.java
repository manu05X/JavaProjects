package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Address;
import org.example.evaluations.evaluation.models.Person;
import org.example.evaluations.evaluation.repos.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonService {

    @Autowired
    private PersonRepo personRepo;

    public Set<String> getAllUniqueCities() {
        Set<String> cities = new HashSet<>();

        List<Person> personList = personRepo.findAll();
        for(Person person : personList) {
            List<Address> addresses = person.getAddresses();
            for(Address address : addresses) {
                cities.add(address.getCity());
            }
        }
        return cities;
    }

    public Set<String> getCitiesWherePersonLivedAt(Long personId) {
        Set<String> cities = new HashSet<>();

        Person person = personRepo.findById(personId).get();
        List<Address> addresses = person.getAddresses();
        for(Address address : addresses) {
            cities.add(address.getCity());
        }

        return cities;
    }
}
