package com.cinetoile.SpringAPI.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Theater_Review_Movie", schema = "cinetoile", catalog = "")
@IdClass(TheaterReviewMoviePK.class)
public class TheaterReviewMovie {
    private String title;
    private String comment;
    private int rate;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int theaterId;
    private int movieId;

    @Basic
    @Column(name = "title", nullable = false, length = 60)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "comment", nullable = false, length = 200)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "rate", nullable = false)
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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

    @Id
    @Column(name = "theaterId", nullable = false)
    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    @Id
    @Column(name = "movieId", nullable = false)
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TheaterReviewMovie that = (TheaterReviewMovie) o;
        return rate == that.rate &&
                theaterId == that.theaterId &&
                movieId == that.movieId &&
                Objects.equals(title, that.title) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, comment, rate, createdAt, updatedAt, theaterId, movieId);
    }
}
