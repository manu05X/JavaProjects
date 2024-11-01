package com.example.AopExample.service;


import com.example.AopExample.entity.Movie;
import com.example.AopExample.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repoMovie;

    public List<Movie> getAllMovies(){
        return repoMovie.findAll();
    }

    public Movie getMovie(Long id){
        Optional<Movie> temp = repoMovie.findById(id);

        Movie m = null;
        if(temp.isEmpty()){
            throw new RuntimeException("User with id {"+ id +"} not found");
        } else {
            m = temp.get();
        }

        return m;
    }

    public Movie addMovie(Movie movie){

        return repoMovie.save(movie);
    }

    public void deleteMovie(Long id){
        Optional<Movie> temp = repoMovie.findById(id);

        Movie m = null;
        if(temp.isEmpty()){
            throw new RuntimeException("User with id {"+ id +"} not found");
        } else {
            m = temp.get();
        }

        repoMovie.delete(m);
    }
}
