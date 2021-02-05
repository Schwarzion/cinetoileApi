package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Data
public class UserReviewMoviePK implements Serializable {
    @Id
    private int movieId;
    @Id
    private int userId;

    public UserReviewMoviePK(int movieId, int userId) {
        this.movieId = movieId;
        this.userId = userId;
    }
}
