package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.models.UserReviewMovie;
import com.cinetoile.SpringAPI.services.UserReviewMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import com.cinetoile.SpringAPI.Dto.In.UserReviewMovieDTOIn;
import com.cinetoile.SpringAPI.Dto.Out.UserReviewMovieDTOOut;
import org.springframework.http.HttpStatus;
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
    UserReviewMovie findByPK(@PathVariable("userId") int userId, @PathVariable("movieId") int movieId){ return this.reviewService.findByUserIdMovieId(userId, movieId);}

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    UserReviewMovieDTOOut addReview(@RequestBody UserReviewMovieDTOIn newReview) { return this.reviewService.addReview(newReview);}

    @DeleteMapping("/reviews/{userId}/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteReview(@PathVariable("userId") int userId, @PathVariable("movieId") int movieId) { this.reviewService.deleteReview(userId, movieId);}

    @PatchMapping("/reviews")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    UserReviewMovie updateReview(@RequestBody UserReviewMovieDTOIn updatedReview) {
        return this.reviewService.updateReview(updatedReview);}
}
