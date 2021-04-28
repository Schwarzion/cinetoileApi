package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {

}
