package com.cinetoile.SpringAPI.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
// 👇 Generate getters & setters, toString and equalsAndHashCode methods
@Data
@Table(name = "User_Review_Movie", schema = "cinetoile", catalog = "")
@IdClass(UserReviewMoviePK.class)
public class UserReviewMovie {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "movieId", nullable = false)
    private int movieId;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "userId", nullable = false)
    private int userId;

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
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;


}
