package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.dto.dtoIn.TheaterMovieDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.TheaterMovieDTOOut;
import com.cinetoile.SpringAPI.services.TheaterMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheaterMovieController {
    private final TheaterMovieService theaterMovieService;

    @Autowired
    TheaterMovieController(TheaterMovieService theaterMovieService) {this.theaterMovieService = theaterMovieService;}

    @GetMapping("/theaterMovies")
    List<TheaterMovieDTOOut> all() {return this.theaterMovieService.findAll();}

    @GetMapping("theaterMovie/{id}")
    TheaterMovieDTOOut findOne(@PathVariable Integer id) {return this.theaterMovieService.findOne(id);}

    @GetMapping("/theater/{theaterId}/movies")
    List<TheaterMovieDTOOut> findbyTheater(@PathVariable Integer theaterId) {return this.theaterMovieService.findByTheaterId(theaterId);}

    @GetMapping("/theaters/movie/{movieId}")
    List<TheaterMovieDTOOut> findbyMovie(@PathVariable Integer movieId) {return this.theaterMovieService.findByMovieId(movieId);}

    @GetMapping("/theater/{theaterId}/movie/{movieId}")
    TheaterMovieDTOOut findByTheaterMovie(@PathVariable Integer theaterId, @PathVariable Integer movieId) {return this.theaterMovieService.findByTheaterIdMovieId(theaterId, movieId);}

    @PostMapping("theaterMovie")
    @ResponseStatus(HttpStatus.CREATED)
    TheaterMovieDTOOut addTheaterMovie(@RequestBody TheaterMovieDTOIn newTheaterMovie) {return this.theaterMovieService.addTheaterMovie(newTheaterMovie);}

    @PutMapping("/theaterMovie/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    TheaterMovieDTOOut updateTheaterMovie(@PathVariable Integer id, @RequestBody TheaterMovieDTOIn updatedTheaterMovie) {return this.theaterMovieService.updateTheaterMovie(id, updatedTheaterMovie);}

    @DeleteMapping("theaterMovie/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTheaterMovie(@PathVariable("id") Integer id) {this.theaterMovieService.deleteTheaterMovie(id);}
}