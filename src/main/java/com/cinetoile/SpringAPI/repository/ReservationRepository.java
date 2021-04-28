package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {

}
