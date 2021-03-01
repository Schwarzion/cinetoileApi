package com.cinetoile.SpringAPI.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Movie")
public class Movie {
    private @Id @GeneratedValue int id;
    private String name;
    private String description;
    private String duration;
    private String tmdbKey;
    private String comment;
    private Integer rate;
    private byte[] image;
    private Timestamp launchDate;
    private String director;
    private String casting;
    private Integer advisedAge;
    private String country;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public Movie() {}

    public Movie(String name, String description, String duration, String tmdbKey, String comment, Integer rate, byte[] image, Timestamp launchDate, String director, String casting, Integer advisedAge, String country, Timestamp createdAt, Timestamp updatedAt) {
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 120)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 300)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "duration", nullable = false, length = 5)
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "tmdbKey", nullable = true, length = 36)
    public String getTmdbKey() {
        return tmdbKey;
    }

    public void setTmdbKey(String tmdbKey) {
        this.tmdbKey = tmdbKey;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = 300)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "rate", nullable = true)
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "image", nullable = true)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Basic
    @Column(name = "launchDate", nullable = false)
    public Timestamp getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Timestamp launchDate) {
        this.launchDate = launchDate;
    }

    @Basic
    @Column(name = "director", nullable = false, length = 100)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "casting", nullable = false, length = 300)
    public String getCasting() {
        return casting;
    }

    public void setCasting(String casting) {
        this.casting = casting;
    }

    @Basic
    @Column(name = "advisedAge", nullable = true)
    public Integer getAdvisedAge() {
        return advisedAge;
    }

    public void setAdvisedAge(Integer advisedAge) {
        this.advisedAge = advisedAge;
    }

    @Basic
    @Column(name = "country", nullable = true, length = 100)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "createdAt", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updatedAt", nullable = false)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Objects.equals(name, movie.name) &&
                Objects.equals(description, movie.description) &&
                Objects.equals(duration, movie.duration) &&
                Objects.equals(tmdbKey, movie.tmdbKey) &&
                Objects.equals(comment, movie.comment) &&
                Objects.equals(rate, movie.rate) &&
                Arrays.equals(image, movie.image) &&
                Objects.equals(launchDate, movie.launchDate) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(casting, movie.casting) &&
                Objects.equals(advisedAge, movie.advisedAge) &&
                Objects.equals(country, movie.country) &&
                Objects.equals(createdAt, movie.createdAt) &&
                Objects.equals(updatedAt, movie.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, description, duration, tmdbKey, comment, rate, launchDate, director, casting, advisedAge, country, createdAt, updatedAt);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
