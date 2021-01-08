package com.cinetoile.SpringAPI.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Theater_Movie", schema = "cinetoile", catalog = "")
@IdClass(TheaterMoviePK.class)
public class TheaterMovie {
    private Timestamp startDate;
    private Timestamp endDate;
    private int status;
    private int theaterFav;
    private int movieId;
    private int theaterId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Basic
    @Column(name = "startDate", nullable = false)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "endDate", nullable = false)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "theaterFav", nullable = false)
    public int getTheaterFav() {
        return theaterFav;
    }

    public void setTheaterFav(int theaterFav) {
        this.theaterFav = theaterFav;
    }

    @Id
    @Column(name = "movieId", nullable = false)
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Id
    @Column(name = "theaterId", nullable = false)
    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
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
        TheaterMovie that = (TheaterMovie) o;
        return status == that.status &&
                theaterFav == that.theaterFav &&
                movieId == that.movieId &&
                theaterId == that.theaterId &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, status, theaterFav, movieId, theaterId, createdAt, updatedAt);
    }
}
