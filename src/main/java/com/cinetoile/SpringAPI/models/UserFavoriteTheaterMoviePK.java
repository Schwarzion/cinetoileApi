package com.cinetoile.SpringAPI.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserFavoriteTheaterMoviePK implements Serializable {
    private int userId;
    private int movieId;
    private int theaterId;

    @Column(name = "userId", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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
        UserFavoriteTheaterMoviePK that = (UserFavoriteTheaterMoviePK) o;
        return userId == that.userId &&
                movieId == that.movieId &&
                theaterId == that.theaterId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId, theaterId);
    }
}
