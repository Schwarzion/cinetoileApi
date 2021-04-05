package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {
}
