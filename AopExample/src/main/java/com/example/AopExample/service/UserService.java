package com.example.AopExample.service;

import com.example.AopExample.entity.User;
import com.example.AopExample.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // Use the correct Logger type from SLF4J
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository repo;

    public List<User> getAllUsers(){
        long startTime = System.currentTimeMillis();
        List<User> users = repo.findAll();
        long endTime = System.currentTimeMillis();

        logger.info("Method getAllPlayers executed in {} ms", (endTime - startTime));
        logger.info("Players fetched: {}", users.size());

        return users;
    }

    public User getUsers(Long id){
        Optional<User> temp = repo.findById(id);

        User u = null;

        if(temp.isEmpty()){
            throw new RuntimeException("User with id {"+ id +"} not found");
        } else {
            u = temp.get();
        }

        return u;
    }

    public User addUser(User user){
        //return repo.save(user);
        try {
            User savedPlayer = repo.save(user);
            logger.info("Users {} successfully added", savedPlayer.getName());
            return savedPlayer;
        } catch (Exception e) {
            logger.error("Failed to add user : {}", user.getName(), e);
            throw e;
        }
    }

    public void deleteUser(Long id){
        Optional<User> temp = repo.findById(id);

        User u = null;

        if(temp.isEmpty()){
            throw new RuntimeException("No User with id : {" + id +"} ");
        } else {
            u = temp.get();
        }

        repo.delete(u);
    }

}
