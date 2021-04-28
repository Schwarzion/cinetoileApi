package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.dto.dtoIn.MovieDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.MovieDTOOut;
import com.cinetoile.SpringAPI.models.MovieEntity;
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
    List<MovieEntity> all() {
        return this.movieService.findAll();
    }

    @GetMapping("/movie/{id}")
    MovieEntity one(@PathVariable Integer id) {
        return this.movieService.findById(id);
    }

    @PostMapping("/movie")
    MovieDTOOut add(@RequestBody MovieDTOIn newMovie) {
        return this.movieService.add(newMovie);
    }

    @PutMapping("/movie/{id}")
    MovieEntity update(@RequestBody MovieEntity newMovie, @PathVariable Integer id) {
        return this.movieService.update(newMovie, id);
    }

    @DeleteMapping("/movie/{id}")
    void delete(@PathVariable Integer id) {
        this.movieService.delete(id);
    }

    /*@GetMapping("/movie/category/{id}")
    Set<Category> categories(@PathVariable Integer id) {
        Movie movie = one(id);
        return movie.getCategories();
    }*/
}
