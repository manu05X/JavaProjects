package com.example.AopExample.service;


import com.example.AopExample.entity.Movie;
import com.example.AopExample.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    // Use the correct Logger type from SLF4J
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private MovieRepository repoMovie;

    public List<Movie> getAllMovies(){
        long startTime = System.currentTimeMillis();
        List<Movie> movies = repoMovie.findAll();
        long endTime = System.currentTimeMillis();
        logger.info("Method getAllMovies executed in {} ms", (endTime - startTime));
        logger.info("Movies fetched: {}", movies.size());
        return movies;
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
        try {
            Movie savedMovie = repoMovie.save(movie);
            logger.info("Movie {} successfully added", savedMovie.getTitle());
            return savedMovie;
        } catch (Exception e) {
            logger.error("Failed to add movie: {}", movie.getTitle(), e);
            throw e;
        }
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
