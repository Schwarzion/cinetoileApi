package com.cinetoile.SpringAPI.dto.dtoOut;


import lombok.Data;

@Data
public class UserReviewMovieDTOOut {
    private String movieTitle;
    private String userFirstName;
    private int rate;

    public UserReviewMovieDTOOut(String movieTitle, String userFirstName, int rate) {
        this.movieTitle = movieTitle;
        this.userFirstName = userFirstName;
        this.rate = rate;
    }
}