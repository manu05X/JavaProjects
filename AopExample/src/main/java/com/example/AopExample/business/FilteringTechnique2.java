package com.example.AopExample.business;


import com.example.AopExample.entity.UserAOP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class FilteringTechnique2 {

    @Autowired
    private UserAOP userAOP;

    public String collaborativeFiltering() {
        String userAOPDetails =  userAOP.getUserDetails();
        return userAOPDetails;
    }
}
