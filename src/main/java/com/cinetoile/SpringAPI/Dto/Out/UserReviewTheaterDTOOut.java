package com.cinetoile.SpringAPI.dto.Out;

public class UserReviewTheaterDTOOut {
    private String theaterName;
    private String userFirstName;
    private int rate;

    public UserReviewTheaterDTOOut(String theaterName, String userFirstName, int rate) {
        this.theaterName = theaterName;
        this.userFirstName = userFirstName;
        this.rate = rate;
    }
}
