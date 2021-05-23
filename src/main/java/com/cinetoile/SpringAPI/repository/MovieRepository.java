package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.Movie;
import com.cinetoile.SpringAPI.models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
}
