package com.cinetoile.SpringAPI.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "Theater_Review_Movie", schema = "cinetoile3", catalog = "")
public class TheaterReviewMovieEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "title", nullable = false, length = 60)
    private String title;

    @Basic
    @Column(name = "comment", nullable = false, length = 200)
    private String comment;

    @Basic
    @Column(name = "rate", nullable = false)
    private int rate;

    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    @Basic
    @Column(name = "theaterId", nullable = false)
    private int theaterId;

    @Basic
    @Column(name = "movieId", nullable = false)
    private int movieId;
}
