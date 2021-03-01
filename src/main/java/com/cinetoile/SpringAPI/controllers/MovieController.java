package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.models.Movie;
import com.cinetoile.SpringAPI.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    List<Movie> all() {
        return this.movieService.findAll();
    }

    @GetMapping("/movie/{id}")
    Movie one(@PathVariable Integer id) {
        return this.movieService.findById(id);
    }

    @PostMapping("/movie/add")
    Movie add(@RequestBody Movie newMovie) {
        return this.movieService.add(newMovie);
    }

    @PutMapping("/movie/edit/{id}")
    Movie update(@RequestBody Movie newUser, @PathVariable Integer id) {
        return this.movieService.update(newUser, id);
    }

    @DeleteMapping("/movie/{id}")
    void delete(@PathVariable Integer id) {
        this.movieService.delete(id);
    }
}