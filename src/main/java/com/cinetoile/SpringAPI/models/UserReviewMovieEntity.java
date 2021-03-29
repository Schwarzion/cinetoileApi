package com.cinetoile.SpringAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "User_Review_Movie")
public class UserReviewMovieEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Basic
    @Column(name = "comment", nullable = false, length = 200)
    private String comment;

    @Basic
    @Column(name = "rate", nullable = false)
    private int rate;

    @Basic
    @JsonIgnore
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @JsonIgnore
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;
}
