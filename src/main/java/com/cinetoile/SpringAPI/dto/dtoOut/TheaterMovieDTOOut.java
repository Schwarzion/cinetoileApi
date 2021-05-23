package com.cinetoile.SpringAPI.dto.dtoOut;

import lombok.Data;

import java.awt.*;
import java.sql.Timestamp;

@Data
public class TheaterMovieDTOOut {
    private Integer id;
    private Integer theaterId;
    private Timestamp startDate;
    private Timestamp endDate;
    private Boolean status;
    private Boolean theaterFav;
    private Integer movieId;
    private byte[] image;
    private String name;
    private String duration;

    public TheaterMovieDTOOut() {};

    public TheaterMovieDTOOut(Integer id, Integer theaterId, Timestamp startDate, Timestamp endDate, Boolean status, Boolean theaterFav, Integer movieId, byte[] image, String name, String duration){
        this.id = id;
        this.theaterId = theaterId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.theaterFav = theaterFav;
        this.movieId = movieId;
        this.image = image;
        this.name = name;
        this.duration = duration;
    }
}
