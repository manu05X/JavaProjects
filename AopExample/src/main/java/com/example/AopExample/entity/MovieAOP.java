package com.example.AopExample.entity;

import org.springframework.stereotype.Repository;

@Repository
public class MovieAOP {

    public String getMovieDetails() {
        return "movie details";
    }
}
