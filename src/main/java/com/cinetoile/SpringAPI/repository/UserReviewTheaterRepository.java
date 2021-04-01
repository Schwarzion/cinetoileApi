package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.UserReviewTheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.List;

public interface UserReviewTheaterRepository extends JpaRepository<UserReviewTheaterEntity, Integer> {
    List<UserReviewTheaterEntity> findById(Id id);
    List<UserReviewTheaterEntity> findByTheaterId(Integer theaterId);
    List<UserReviewTheaterEntity> findByUserId(Integer userId);
    UserReviewTheaterEntity findByTheaterIdAndUserId(Integer theaterId, Integer userId);

    void deleteById(Id id);
}
