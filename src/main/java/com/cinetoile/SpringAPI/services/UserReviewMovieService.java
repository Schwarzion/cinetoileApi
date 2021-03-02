package com.cinetoile.SpringAPI.services;
import com.cinetoile.SpringAPI.Dto.Out.UserReviewMovieDTOOut;
import com.cinetoile.SpringAPI.Dto.In.UserReviewMovieDTOIn;
import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.models.Movie;
import com.cinetoile.SpringAPI.models.User;
import com.cinetoile.SpringAPI.models.UserReviewMovie;
import com.cinetoile.SpringAPI.models.UserReviewMoviePK;
import com.cinetoile.SpringAPI.repository.UserReviewMovieRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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

    public UserReviewMovie findByUserIdMovieId(int userId, int movieId) {
        return repository.findByIdUserIdAndIdMovieId(userId, movieId);
    }

    public UserReviewMovieDTOOut addReview(UserReviewMovieDTOIn newReview) {
        Movie movie = movieService.findById(newReview.getMovieId());
        User user = userService.findById(newReview.getUserId());
        UserReviewMovie review = new UserReviewMovie(
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
    }

    public UserReviewMovie findById(UserReviewMoviePK id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("userReviewMovie", id.toString()));
    }

    public UserReviewMovie updateReview(UserReviewMovieDTOIn newReview) {
         UserReviewMovie reviewToUpdate = repository.findByIdUserIdAndIdMovieId(newReview.getUserId(), newReview.getMovieId());
        if(reviewToUpdate == null) {
            throw(new NotFoundException("userReviewMovie", newReview.toString()));
        } else {
            reviewToUpdate.setComment(newReview.getComment());
            reviewToUpdate.setRate(newReview.getRate());
            reviewToUpdate.setTitle(newReview.getTitle());
            reviewToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
            return repository.save(reviewToUpdate);
        }
    }

    public void deleteReview(int userId, int movieId) { repository.deleteByIdUserIdAndIdMovieId(userId, movieId);}
}



