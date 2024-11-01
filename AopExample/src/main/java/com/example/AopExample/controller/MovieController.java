package com.example.AopExample.controller;

import com.example.AopExample.entity.Movie;
import com.example.AopExample.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Fetch all movies
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    // Fetch a specific movie by ID
    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id) {
        return movieService.getMovie(id);
    }

    // Add a new movie (ID will be generated automatically by the database)
    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        // ID is auto-generated, so don't manually set it
        return movieService.addMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
    }
}
