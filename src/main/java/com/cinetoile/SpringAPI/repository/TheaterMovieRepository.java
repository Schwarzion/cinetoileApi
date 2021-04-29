package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.TheaterMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface TheaterMovieRepository extends JpaRepository<TheaterMovieEntity, Integer> {
    TheaterMovieEntity findById(Id id);
    List<TheaterMovieEntity> findByTheaterId(Integer theaterId);
    List<TheaterMovieEntity> findByMovieId(Integer movieId);
    TheaterMovieEntity findByTheaterIdAndMovieId(Integer theaterId, Integer movieId);
}
