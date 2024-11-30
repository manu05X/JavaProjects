package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Sex;
import org.example.evaluations.evaluation.models.User;
import org.example.evaluations.evaluation.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSearchService {

    @Autowired
    private UserRepository userRepository;

    private final Integer pageSize = 5;

    public List<User> getUsersHavingAddress(String address,Integer pageNumber) {
        return userRepository.findUserByAddressEquals(address, PageRequest.of(pageNumber,pageSize));
    }

    public List<User> getDetailsOfAllLadies(Integer pageNumber) {
        return userRepository.findUserBySexEquals(Sex.FEMALE,PageRequest.of(pageNumber,pageSize));
    }

    public List<User> getDetailsOfAllAdultMales(Integer pageNumber) {
        return userRepository.findUserBySexAndAgeGreaterThanEqual(Sex.MALE,18,PageRequest.of(pageNumber,pageSize));
    }
}
