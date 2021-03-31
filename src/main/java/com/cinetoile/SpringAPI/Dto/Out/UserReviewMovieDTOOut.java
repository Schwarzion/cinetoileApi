package com.cinetoile.SpringAPI.Dto.Out;


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