package com.cinetoile.SpringAPI.services;


import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.models.User;
import com.cinetoile.SpringAPI.models.UserReviewMovie;
import com.cinetoile.SpringAPI.models.UserReviewMoviePK;
import com.cinetoile.SpringAPI.repository.UserReviewMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewMovieService {

    private final UserReviewMovieRepository repository;

    UserReviewMovieService(UserReviewMovieRepository repository) { this.repository = repository;}

    public List<UserReviewMovie> findAll() { return repository.findAll();}

    public UserReviewMovie addUser(UserReviewMovie newReview) { return repository.save(newReview);}

    public UserReviewMovie findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("userReviewMovie", id));
    }

  /*  public UserReviewMovie updateReview(UserReviewMovie newReview, UserReviewMoviePK id) {
        return repository.findById(id).map(review -> {
                review.setComment(newReview.getComment());
                review.setRate(newReview.getRate());
                review.setTitle(newReview.getTitle());

                return repository.save(review);
        }).orElseGet(() -> {
            newReview.setId();
        });
    } */

    public void deleteReview(Integer id) { repository.deleteById(id);}
}



