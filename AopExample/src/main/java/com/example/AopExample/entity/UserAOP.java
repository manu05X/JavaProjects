package com.example.AopExample.entity;

import org.springframework.stereotype.Repository;

@Repository
public class UserAOP {

    public String getUserDetails() {
        return "user details";
    }
}
