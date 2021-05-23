package com.cinetoile.SpringAPI.models;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "Theater_Movie")
public class TheaterMovieEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "startDate", nullable = false)
    private Timestamp startDate;

    @Basic
    @Column(name = "endDate", nullable = false)
    private Timestamp endDate;

    @Basic
    @Column(name = "status", nullable = false)
    private Boolean status;

    @Basic
    @Column(name = "theaterFav", nullable = false)
    private Boolean theaterFav;

    @ManyToOne(targetEntity = MovieEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId", nullable = false, referencedColumnName = "id")
    private MovieEntity movie;

    @ManyToOne(targetEntity = TheaterEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "theaterId", nullable = false, referencedColumnName = "id")
    private TheaterEntity theater;

    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    public TheaterMovieEntity() {};

    public TheaterMovieEntity(Timestamp startDate, Timestamp endDate, Boolean status, Boolean theaterFav, MovieEntity movie, TheaterEntity theater){
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.theaterFav = theaterFav;
        this.movie = movie;
        this.theater = theater;
        this.createdAt = new Timestamp(new Date().getTime());
        this.updatedAt = new Timestamp(new Date().getTime());
    }
}
