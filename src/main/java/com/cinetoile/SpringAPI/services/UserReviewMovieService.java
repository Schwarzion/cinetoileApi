package com.cinetoile.SpringAPI.services;
import com.cinetoile.SpringAPI.dto.dtoOut.RoomDTOOut;
import com.cinetoile.SpringAPI.dto.dtoOut.UserReviewMovieDTOOut;
import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.UserReviewMovieDTOIn;
import com.cinetoile.SpringAPI.models.*;
import com.cinetoile.SpringAPI.repository.UserReviewMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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


    private UserReviewMovieDTOOut convertToUserReviewMovieDto(UserReviewMovieEntity review) {
        return new UserReviewMovieDTOOut(
                review.getTitle(),
                review.getComment(),
                review.getMovieId().getName(),
                review.getUserId().getFirstname(),
                review.getRate()
        );
    }

    private List<UserReviewMovieDTOOut> convertToListUserReviewMovieDto(List<UserReviewMovieEntity> list) {
        return list.stream().map(review -> new UserReviewMovieDTOOut(
                review.getTitle(),
                review.getComment(),
                review.getMovieId().getName(),
                review.getUserId().getFirstname(),
                review.getRate()
        )).collect(Collectors.toList());
    }

    public List<UserReviewMovieDTOOut> findAll() {
        return convertToListUserReviewMovieDto(repository.findAll());
    }

    public List<UserReviewMovieDTOOut> findAllByUserId(int userId) {
        return convertToListUserReviewMovieDto(repository.findByUserId(userId));
    }

    public List<UserReviewMovieDTOOut> findAllByMovieId(int movieId) {
        return convertToListUserReviewMovieDto(repository.findByMovieId(movieId));
    }

    public UserReviewMovieDTOOut findByUserIdMovieId(int userId, int movieId) {
        return convertToUserReviewMovieDto(repository.findByUserIdAndMovieId(userId, movieId));
    }

    public UserReviewMovieEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("userReviewMovie", id.toString()));
    }

    public UserReviewMovieDTOOut findDto(Integer id) {
        UserReviewMovieEntity review = repository.findById(id).orElseThrow(() -> new NotFoundException("userReviewMovie", id.toString()));
        return convertToUserReviewMovieDto(review);
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
                newReview.getTitle(),
                newReview.getComment(),
                movie.getName(),
                user.getFirstname(),
                newReview.getRate()
        );
    }

    public UserReviewMovieDTOOut updateReview(UserReviewMovieDTOIn newReview) {
         UserReviewMovieEntity reviewToUpdate = repository.findByUserIdAndMovieId(newReview.getUserId(), newReview.getMovieId());
        if(reviewToUpdate == null) {
            throw(new NotFoundException("userReviewMovie", newReview.toString()));
        } else {
            reviewToUpdate.setComment(newReview.getComment());
            reviewToUpdate.setRate(newReview.getRate());
            reviewToUpdate.setTitle(newReview.getTitle());
            reviewToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
            repository.save(reviewToUpdate);
            return convertToUserReviewMovieDto(reviewToUpdate);
        }
    }

    public void deleteReview(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}



