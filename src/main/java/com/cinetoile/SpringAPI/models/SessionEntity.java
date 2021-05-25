package com.cinetoile.SpringAPI.models;

import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@EnableJpaAuditing
@Table(name = "Session")
public class SessionEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public SessionEntity() {
    }

    public SessionEntity(RoomEntity roomId, MovieEntity movieId, Timestamp time) {
        this.roomId = roomId;
        this.movieId = movieId;
        this.time = time;
        this.placeLeft = roomId.getPlace();
    }

    @Basic
    @Column(name = "time", nullable = false)
    private Timestamp time;

    @Basic
    @Column(name = "placeLeft", nullable = false)
    private Integer placeLeft;

    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    @OneToOne(targetEntity = MovieEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId", referencedColumnName = "id")
    private MovieEntity movieId;

    @OneToOne(targetEntity = RoomEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId", referencedColumnName = "id")
    private RoomEntity roomId;
}
