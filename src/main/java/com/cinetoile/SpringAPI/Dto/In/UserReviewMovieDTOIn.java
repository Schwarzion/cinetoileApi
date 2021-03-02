package com.cinetoile.SpringAPI.Dto.In;


import com.cinetoile.SpringAPI.models.UserReviewMovie;
import lombok.Data;

@Data
public class UserReviewMovieDTOIn {

    private int movieId;
    private int userId;
    private String title;
    private String comment;
    private int rate;

    public UserReviewMovieDTOIn(int movieId, int userId, String title, String comment, int rate) {
        this.movieId = movieId;
        this.userId = userId;
        this.title = title;
        this.comment = comment;
        this.rate = rate;
    }
}