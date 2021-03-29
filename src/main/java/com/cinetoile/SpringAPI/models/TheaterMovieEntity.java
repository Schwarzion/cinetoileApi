package com.cinetoile.SpringAPI.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "Theater_Movie")
public class TheaterMovieEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "startDate", nullable = false)
    private Timestamp startDate;

    @Basic
    @Column(name = "endDate", nullable = false)
    private Timestamp endDate;

    @Basic
    @Column(name = "status", nullable = false)
    private int status;

    @Basic
    @Column(name = "theaterFav", nullable = false)
    private int theaterFav;

    @Basic
    @Column(name = "movieId", nullable = false)
    private int movieId;

    @Basic
    @Column(name = "theaterId", nullable = false)
    private int theaterId;

    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;
}
