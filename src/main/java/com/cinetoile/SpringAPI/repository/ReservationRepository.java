package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.ReservationEntity;
import com.cinetoile.SpringAPI.models.SessionEntity;
import com.cinetoile.SpringAPI.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
    List<ReservationEntity> findAllByUserId(UserEntity user);

    @Query("select a from ReservationEntity a where a.userId.id = :userId and a.sessionId.time < :sessionTime")
    List<ReservationEntity> findAllPassedByUser(
            @Param("sessionTime") Date sessionTime,
            @Param("userId") Integer id);

    @Query("select a from ReservationEntity a where a.userId.id = :userId and a.sessionId.time >= :sessionTime")
    List<ReservationEntity> findAllIncomingByUser(
            @Param("sessionTime") Date sessionTime,
            @Param("userId") Integer id);

    List<ReservationEntity> findAllBySessionIdOrderByStatus(SessionEntity session);
}
