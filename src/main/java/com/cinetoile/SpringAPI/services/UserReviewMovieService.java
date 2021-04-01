package com.cinetoile.SpringAPI.services;
import com.cinetoile.SpringAPI.dto.Out.UserReviewMovieDTOOut;
import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.In.UserReviewMovieDTOIn;
import com.cinetoile.SpringAPI.dto.Out.UserReviewMovieDTOOut;
import com.cinetoile.SpringAPI.models.*;
import com.cinetoile.SpringAPI.repository.UserReviewMovieRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.transaction.Transactional;
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

    public List<UserReviewMovieEntity> findAll() {
        return repository.findAll();
    }

    public List<UserReviewMovieEntity> findAllByUserId(int userId) {
        return repository.findByUserId(userId);
    }

    public List<UserReviewMovieEntity> findAllByMovieId(int movieId) {
        return repository.findByMovieId(movieId);
    }

    public UserReviewMovieEntity findByUserIdMovieId(int userId, int movieId) {
        return repository.findByUserIdAndMovieId(userId, movieId);
    }

    public UserReviewMovieEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("userReviewMovie", id.toString()));
    }

    public UserReviewMovieDTOOut addReview(UserReviewMovieDTOIn newReview) {
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
    }

    public UserReviewMovieEntity updateReview(UserReviewMovieDTOIn newReview) {
         UserReviewMovieEntity reviewToUpdate = repository.findByUserIdAndMovieId(newReview.getUserId(), newReview.getMovieId());
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

    public void deleteReview(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}



