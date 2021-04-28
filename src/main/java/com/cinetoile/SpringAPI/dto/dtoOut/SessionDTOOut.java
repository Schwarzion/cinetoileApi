package com.cinetoile.SpringAPI.dto.dtoOut;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class SessionDTOOut {
    private Timestamp time;
    private Integer movieId;
    private Integer roomId;

    public SessionDTOOut() {
    }

    public SessionDTOOut(Timestamp time, Integer movieId, Integer roomId) {
        this.time = time;
        this.movieId = movieId;
        this.roomId = roomId;
    }
}