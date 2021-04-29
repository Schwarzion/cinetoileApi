package com.cinetoile.SpringAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Movie")
@NoArgsConstructor
public class MovieEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Basic
    @Column(name = "description", nullable = true, length = 300)
    private String description;

    @Basic
    @Column(name = "duration", nullable = false, length = 5)
    private String duration;

    @Basic
    @Column(name = "tmdbKey", nullable = true, length = 36)
    private String tmdbKey;

    @Basic
    @Column(name = "comment", nullable = true, length = 300)
    private String comment;

    @Basic
    @Column(name = "rate", nullable = true)
    private Integer rate;

    @Basic
    @Column(name = "image", nullable = true)
    private byte[] image;

    @Basic
    @Column(name = "launchDate", nullable = false)
    private Timestamp launchDate;

    @Basic
    @Column(name = "director", nullable = false, length = 100)
    private String director;

    @Basic
    @Column(name = "casting", nullable = false, length = 300)
    private String casting;

    @Basic
    @Column(name = "advisedAge", nullable = true)
    private Integer advisedAge;

    @Basic
    @Column(name = "country", nullable = true, length = 100)
    private String country;

    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "movieId", targetEntity = UserReviewMovieEntity.class, fetch = FetchType.EAGER)
    public List<UserReviewMovieEntity> userReviewMovies;

    public MovieEntity(
            String name,
            String description,
            String duration,
            String tmdbKey,
            String comment,
            int rate,
            byte[] image,
            Timestamp launchDate,
            String director,
            String casting,
            int advisedAge,
            String country
    ) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.tmdbKey = tmdbKey;
        this.comment = comment;
        this.rate = rate;
        this.image = image;
        this.launchDate = launchDate;
        this.director = director;
        this.casting = casting;
        this.advisedAge = advisedAge;
        this.country = country;
        this.createdAt = new Timestamp(new Date().getTime());
        this.updatedAt = new Timestamp(new Date().getTime());
    }
}
