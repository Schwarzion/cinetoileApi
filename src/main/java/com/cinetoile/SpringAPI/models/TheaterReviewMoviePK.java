package com.cinetoile.SpringAPI.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TheaterReviewMoviePK implements Serializable {
    private int theaterId;
    private int movieId;

    @Column(name = "theaterId", nullable = false)
    @Id
    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    @Column(name = "movieId", nullable = false)
    @Id
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
        TheaterReviewMoviePK that = (TheaterReviewMoviePK) o;
        return theaterId == that.theaterId &&
                movieId == that.movieId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(theaterId, movieId);
    }
}
