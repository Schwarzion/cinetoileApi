package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.DtoThatWillBeMoved.UserReviewMovieDTO;
import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.models.Movie;
import com.cinetoile.SpringAPI.models.User;
import com.cinetoile.SpringAPI.models.UserReviewMovie;
import com.cinetoile.SpringAPI.models.UserReviewMoviePK;
import com.cinetoile.SpringAPI.repository.UserReviewMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewMovieService {

    private final UserReviewMovieRepository repository;
    private final UserService userService;

    private final MovieService movieService;

    UserReviewMovieService(UserReviewMovieRepository repository, UserService userService, MovieService movieService) {
        this.userService = userService;
        this.movieService = movieService;
        this.repository = repository;
    }

    public List<UserReviewMovie> findAll() {
        return repository.findAll();
    }

    public List<UserReviewMovie> findAllByUserId(int userId) {
        return repository.findByIdUserId(userId);
    }

    public List<UserReviewMovie> findAllByMovieId(int movieId) {
        return repository.findByIdMovieId(movieId);
    }

    public List<UserReviewMovie> findByUserIdMovieId(int userId, int movieId) {
        return repository.findByIdUserIdAndIdMovieId(userId, movieId);
    }

    public UserReviewMovie addReview(UserReviewMovieDTO newReview) {
        User user = userService.findById(newReview.getUserId());
        Movie movie = movieService.findById(newReview.getMovieId());
        UserReviewMovie review = new UserReviewMovie(
                newReview.getTitle(),
                newReview.getComment(),
                newReview.getRate()
        );
        return repository.save(review);
    }

    public UserReviewMovie findById(UserReviewMoviePK id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("userReviewMovie", id.toString()));
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
    //public void deleteReview(Integer id) { repository.deleteById(id);}
}



