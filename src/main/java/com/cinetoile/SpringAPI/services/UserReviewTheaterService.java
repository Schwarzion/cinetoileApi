package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.In.UserReviewMovieDTOIn;
import com.cinetoile.SpringAPI.dto.Out.UserReviewMovieDTOOut;
import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.models.UserEntity;
import com.cinetoile.SpringAPI.models.UserReviewMovieEntity;
import com.cinetoile.SpringAPI.models.UserReviewTheaterEntity;
import com.cinetoile.SpringAPI.repository.UserReviewTheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewTheaterService {

    private final UserReviewTheaterRepository repository;

    private final UserService userService;

    UserReviewTheaterService(UserReviewTheaterRepository repository) {
        this.repository = repository;
    }

    public UserReviewTheaterEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("userReviewTheater", id.toString()));
    }

    public List<UserReviewTheaterEntity> findAll() {
        return repository.findAll();
    }

    public List<UserReviewTheaterEntity> findAllByUserId(Integer userId) {
        return repository.findByUserId(userId);
    }

    public List<UserReviewTheaterEntity> findAllByTheaterId(Integer theaterId) {
        return repository.findByTheaterId(theaterId);
    }


    // TODO:
   /* public UserReviewMovieDTOOut addReview(UserReviewMovieDTOIn newReview) {
        MovieEntity movie = movieService.findById(newReview.getMovieId());
        UserEntity user = userService.findById(newReview.getUserId());
        UserReviewMovieEntity review = new UserReviewMovieEntity(
                movie,
                user,
                newReview.getTitle(),
                newReview.getComment(),
                newReview.getRate()
        );
        repository.save(review);
        return new UserReviewMovieDTOOut(
                movie.getName(),
                user.getFirstname(),
                newReview.getRate()
        );
    } */

    public void deleteReview(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
