package com.example.AopExample.service;

import com.example.AopExample.entity.User;
import com.example.AopExample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> getAllUsers(){
        return repo.findAll();
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
        return repo.save(user);
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
