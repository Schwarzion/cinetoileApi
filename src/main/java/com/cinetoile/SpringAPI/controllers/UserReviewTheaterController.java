package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.dto.In.UserReviewMovieDTOIn;
import com.cinetoile.SpringAPI.dto.In.UserReviewTheaterDTOIn;
import com.cinetoile.SpringAPI.dto.Out.UserReviewMovieDTOOut;
import com.cinetoile.SpringAPI.dto.Out.UserReviewTheaterDTOOut;
import com.cinetoile.SpringAPI.models.UserReviewTheaterEntity;
import com.cinetoile.SpringAPI.services.UserReviewTheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserReviewTheaterController {

    private final UserReviewTheaterService reviewTheaterService;

    @Autowired
    UserReviewTheaterController(UserReviewTheaterService reviewTheaterService) {
        this.reviewTheaterService = reviewTheaterService;
    }

    @GetMapping("/theater/reviews/")
    List<UserReviewTheaterEntity> all(){ return this.reviewTheaterService.findAll();}

    @GetMapping("/theater/reviews/{userId}")
    List<UserReviewTheaterEntity> userAll(@PathVariable("userId") int userId) { return this.reviewTheaterService.findAllByUserId(userId);}

    @GetMapping("/theater/reviews/{theaterId}")
    List<UserReviewTheaterEntity> theaterAll(@PathVariable("theaterId") int theaterId){ return this.reviewTheaterService.findAllByTheaterId(theaterId);}

    @GetMapping("/theater/reviews/{id}")
    UserReviewTheaterEntity findById(@PathVariable("id") Integer id){ return this.reviewTheaterService.findById(id);}

    @PostMapping("/theater/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    UserReviewTheaterDTOOut addReview(@RequestBody UserReviewTheaterDTOIn newReview) { return this.reviewTheaterService.addReview(newReview);}

    @DeleteMapping("/theater/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteReview(@PathVariable("id") Integer id ) { this.reviewTheaterService.deleteReview(id);}

   /* @PatchMapping("/reviews")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    UserReviewTheaterEntity updateReview(@RequestBody UserReviewTheaterDTOIn updatedReview) {
        return this.reviewTheaterService.updateReview(updatedReview);} */
}
