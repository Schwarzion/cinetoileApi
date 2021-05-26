package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.models.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Table(catalog = "Session")
@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {

    List<SessionEntity> findByMovieId(MovieEntity movie);

    List<SessionEntity> findByMovieIdAndTimeBetween(
            MovieEntity movieId,
            Date start,
            Date end
    );

    @Query("select a from SessionEntity a where a.roomId.theater.id = :theaterId and a.time >= :startDate and a.time <= :endDate ")
    List<SessionEntity> findByTheaterId(@Param("theaterId") Integer theaterId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
