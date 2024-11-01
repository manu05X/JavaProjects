package com.example.AopExample.business;


import com.example.AopExample.entity.MovieAOP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AopExample.aspect.MeasureTime;


@Service
public class FilteringTechnique1 {


    @Autowired
    private MovieAOP movieAOP;

    @MeasureTime
    public String contentBasedFiltering() {
        String movieAOPDetails =  movieAOP.getMovieDetails();
        return movieAOPDetails;
    }
}
