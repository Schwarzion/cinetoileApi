package com.cinetoile.SpringAPI.models;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Objects;

public class UserReviewMoviePK implements Serializable {
    private int movieId;
    private int userId;

    @Column(name = "movieId", nullable = false)
    @Id
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Column(name = "userId", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserReviewMoviePK that = (UserReviewMoviePK) o;
        return movieId == that.movieId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, userId);
    }
}
