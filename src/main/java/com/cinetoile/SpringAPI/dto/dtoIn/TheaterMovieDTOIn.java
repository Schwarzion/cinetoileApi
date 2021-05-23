package com.cinetoile.SpringAPI.dto.dtoIn;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class TheaterMovieDTOIn {
    private Integer theaterId;
    private Integer movieId;
    private Timestamp startDate;
    private Timestamp endDate;
    private Boolean status;
    private Boolean theaterFav;

    public TheaterMovieDTOIn() {};

    public TheaterMovieDTOIn(Integer theaterId, Integer movieId, Timestamp startDate, Timestamp endDate, Boolean status, Boolean theaterFav){
        this.theaterId = theaterId;
        this.movieId = movieId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.theaterFav = theaterFav;
    }
}
