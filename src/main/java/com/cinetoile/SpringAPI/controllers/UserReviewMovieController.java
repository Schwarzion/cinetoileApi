package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.DtoThatWillBeMoved.UserReviewMovieDTO;
import com.cinetoile.SpringAPI.models.UserReviewMovie;
import com.cinetoile.SpringAPI.services.UserReviewMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserReviewMovieController {

    private final UserReviewMovieService reviewService;

    @Autowired
    UserReviewMovieController(UserReviewMovieService reviewService){ this.reviewService = reviewService ;}

    @GetMapping("/reviews")
    List<UserReviewMovie> all(){ return this.reviewService.findAll();}

    @GetMapping("/reviews/{userId}")
    List<UserReviewMovie> userAll(@PathVariable("userId") int userId) { return this.reviewService.findAllByUserId(userId);}

    @GetMapping("/reviews/{movieId}")
    List<UserReviewMovie> movieAll(@PathVariable("movieId") int movieId){ return this.reviewService.findAllByMovieId(movieId);}

    @GetMapping("/reviews/{userId}/{movieId}")
    List<UserReviewMovie> findByPK(@PathVariable("userId") int userId, @PathVariable("movieId") int movieId){ return this.reviewService.findByUserIdMovieId(userId, movieId);}

    @PostMapping("/reviews")
    UserReviewMovie addReview(@RequestBody UserReviewMovieDTO newReview) { return this.reviewService.addReview(newReview);}
}
