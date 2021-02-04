package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.Category;
import com.cinetoile.SpringAPI.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
