package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.ReservationEntity;
import com.cinetoile.SpringAPI.models.SessionEntity;
import com.cinetoile.SpringAPI.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Table(catalog = "Reservation")
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

    @Query(nativeQuery = true, value="SELECT userId, status, id, sessionId, princingId, createdAt, updatedAt FROM Reservation AS res WHERE res.sessionId = 10 and status != 2 ORDER BY status")
    List<ReservationEntity> findAllBySessionIdOrderByStatus(SessionEntity session);
}
