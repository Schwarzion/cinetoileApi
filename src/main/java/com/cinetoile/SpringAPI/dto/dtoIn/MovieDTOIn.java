package com.cinetoile.SpringAPI.dto.dtoIn;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class MovieDTOIn {
    private String name;
    private String description;
    private String duration;
    private String tmdbKey;
    private String comment;
    private Integer rate;
    private byte[] image;
    private Timestamp launchDate;
    private String director;
    private String casting;
    private Integer advisedAge;
    private String country;

    public MovieDTOIn() {};

    public MovieDTOIn(String name, String description, String duration, String tmdbKey, String comment, Integer rate, byte[] image, Timestamp launchDate, String director, String casting, Integer advisedAge, String country){
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.tmdbKey = tmdbKey;
        this.comment = comment;
        this.rate = rate;
        this.image = image;
        this.launchDate = launchDate;
        this.director = director;
        this.casting = casting;
        this.advisedAge = advisedAge;
        this.country = country;
    }
}
