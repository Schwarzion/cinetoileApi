package com.cinetoile.SpringAPI.dto.dtoOut;

import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.models.TheaterEntity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TheaterMovieDTOOut {
    private int id;
    private Integer theaterId;
    private Integer movieId;
    private Timestamp startDate;
    private Timestamp endDate;
    private Boolean status;
    private Boolean theaterFav;

    public TheaterMovieDTOOut() {};

    public TheaterMovieDTOOut(Integer id, Integer theaterId, Integer movieId, Timestamp startDate, Timestamp endDate, Boolean status, Boolean theaterFav){
        this.id = id;
        this.theaterId = theaterId;
        this.movieId = movieId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.theaterFav = theaterFav;
    }
}
