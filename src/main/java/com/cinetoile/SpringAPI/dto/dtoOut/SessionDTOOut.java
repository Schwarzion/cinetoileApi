package com.cinetoile.SpringAPI.dto.dtoOut;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SessionDTOOut {
    private Integer id;
    private Timestamp time;
    private Integer movieId;
    private Integer roomId;
    private Integer placeLeft;

    public SessionDTOOut() {
    }

    public SessionDTOOut(Integer id, Timestamp time, Integer movieId, Integer roomId, Integer placeLeft) {
        this.id = id;
        this.time = time;
        this.movieId = movieId;
        this.roomId = roomId;
        this.placeLeft = placeLeft;
    }
}