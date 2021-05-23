package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.UserReviewMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserReviewMovieRepository extends JpaRepository <UserReviewMovieEntity, Integer> {
    List<UserReviewMovieEntity> findById(Id id);
    List<UserReviewMovieEntity> findByMovieId(Integer movieId);
    List<UserReviewMovieEntity> findByUserId(int userId);
    UserReviewMovieEntity findByUserIdAndMovieId(int userId, int movieId);
    @Transactional
    void deleteById(Id id);
}
