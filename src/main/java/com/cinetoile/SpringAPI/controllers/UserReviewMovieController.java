package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.dto.In.UserReviewMovieDTOIn;
import com.cinetoile.SpringAPI.models.UserReviewMovieEntity;
import com.cinetoile.SpringAPI.services.UserReviewMovieService;
import org.springframework.beans.factory.annotation.Autowired;
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
    List<UserReviewMovieEntity> all(){ return this.reviewService.findAll();}

    @GetMapping("/reviews/{userId}")
    List<UserReviewMovieEntity> userAll(@PathVariable("userId") int userId) { return this.reviewService.findAllByUserId(userId);}

    @GetMapping("/reviews/{movieId}")
    List<UserReviewMovieEntity> movieAll(@PathVariable("movieId") int movieId){ return this.reviewService.findAllByMovieId(movieId);}

    @GetMapping("/reviews/{id}")
    UserReviewMovieEntity findById(@PathVariable("id") Integer id){ return this.reviewService.findById(id);}

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    UserReviewMovieDTOOut addReview(@RequestBody UserReviewMovieDTOIn newReview) { return this.reviewService.addReview(newReview);}

    @DeleteMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteReview(@PathVariable("id") Integer id ) { this.reviewService.deleteReview(id);}

    @PatchMapping("/reviews")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    UserReviewMovieEntity updateReview(@RequestBody UserReviewMovieDTOIn updatedReview) {
        return this.reviewService.updateReview(updatedReview);}
}
