package com.cinetoile.SpringAPI.Dto;


import lombok.Data;

@Data
public class UserReviewMovieDTO {

    private int movieId;
    private int userId;
    private String title;
    private String comment;
    private int rate;
}
