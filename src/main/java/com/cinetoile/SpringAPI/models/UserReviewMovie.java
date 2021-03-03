package com.cinetoile.SpringAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Entity
// 👇 Generate getters & setters, toString and equalsAndHashCode methods
@Data
@Table(name = "User_Review_Movie", schema = "cinetoile", catalog = "")
@NoArgsConstructor
public class UserReviewMovie {
    @EmbeddedId
    private UserReviewMoviePK id;

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

    public UserReviewMovie(UserReviewMoviePK id, String title, String comment, int rate) {
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.rate = rate;
        this.createdAt = new Timestamp(new Date().getTime());
        this.updatedAt = new Timestamp(new Date().getTime());
    }
}
