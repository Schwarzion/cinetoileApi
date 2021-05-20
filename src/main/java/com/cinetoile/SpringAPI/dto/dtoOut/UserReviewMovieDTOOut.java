package com.cinetoile.SpringAPI.dto.dtoOut;


import lombok.Data;

@Data
public class UserReviewMovieDTOOut {
    private String title;
    private String comment;
    private String movieTitle;
    private String userFirstName;
    private int rate;

    public UserReviewMovieDTOOut(String title, String comment, String movieTitle, String userFirstName, int rate) {
        this.title = title;
        this.comment = comment;
        this.movieTitle = movieTitle;
        this.userFirstName = userFirstName;
        this.rate = rate;
    }
}