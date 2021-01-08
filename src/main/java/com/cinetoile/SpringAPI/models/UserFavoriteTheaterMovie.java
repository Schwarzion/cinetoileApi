package com.cinetoile.SpringAPI.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "User_Favorite_TheaterMovie", schema = "cinetoile", catalog = "")
@IdClass(UserFavoriteTheaterMoviePK.class)
public class UserFavoriteTheaterMovie {
    private int userId;
    private int movieId;
    private int theaterId;

    @Id
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFavoriteTheaterMovie that = (UserFavoriteTheaterMovie) o;
        return userId == that.userId &&
                movieId == that.movieId &&
                theaterId == that.theaterId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId, theaterId);
    }
}
