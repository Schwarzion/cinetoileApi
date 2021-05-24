package com.cinetoile.SpringAPI.dto.dtoIn;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class SessionDTOIn {
    private Timestamp time;
    private Integer movieId;
    private Integer roomId;

    public SessionDTOIn() {
    }

    public SessionDTOIn(Timestamp time, Integer movieId, Integer roomId) {
        this.time = time;
        this.movieId = movieId;
        this.roomId = roomId;
    }
}