package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.UserReviewMovie;
import com.cinetoile.SpringAPI.models.UserReviewMoviePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReviewMovieRepository extends JpaRepository <UserReviewMovie, UserReviewMoviePK> {
    List<UserReviewMovie> findByIdMovieId(Integer movieId);
    List<UserReviewMovie> findByIdUserId(int userId);
    List<UserReviewMovie> findByIdUserIdAndIdMovieId(int userId, int movieId);
}
