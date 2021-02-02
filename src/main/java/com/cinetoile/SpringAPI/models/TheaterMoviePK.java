package com.cinetoile.SpringAPI.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TheaterMoviePK implements Serializable {
    private int movieId;
    private int theaterId;

    @Column(name = "movieId", nullable = false)
    @Id
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Column(name = "theaterId", nullable = false)
    @Id
    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TheaterMoviePK that = (TheaterMoviePK) o;
        return movieId == that.movieId &&
                theaterId == that.theaterId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, theaterId);
    }
}
