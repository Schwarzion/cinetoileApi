package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.UserReviewMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewMovieRepository extends JpaRepository <UserReviewMovie, Integer> {
}
