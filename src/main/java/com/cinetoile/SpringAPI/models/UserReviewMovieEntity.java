package com.cinetoile.SpringAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
// 👇 Generate getters & setters, toString and equalsAndHashCode methods
@Data
@Table(name = "User_Review_Movie", schema = "cinetoile")
@NoArgsConstructor
public class UserReviewMovieEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = MovieEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name= "movieId", nullable = false, referencedColumnName = "id")
    private MovieEntity movieId;

    @ManyToOne(targetEntity = UserEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name= "userId", nullable = false, referencedColumnName = "id")
    private UserEntity userId;

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


    public UserReviewMovieEntity(MovieEntity movieId, UserEntity userId, String title, String comment, int rate) {
        this.movieId = movieId;
        this.userId = userId;
        this.title = title;
        this.comment = comment;
        this.rate = rate;
        this.createdAt = new Timestamp(new Date().getTime());
        this.updatedAt = new Timestamp(new Date().getTime());
    }
}
