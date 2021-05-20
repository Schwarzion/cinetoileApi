package com.cinetoile.SpringAPI.dto.dtoOut;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class SessionDTOOut {
    private Timestamp time;
    private Integer movieId;
    private Integer roomId;
    private Integer placeLeft;

    public SessionDTOOut() {
    }

    public SessionDTOOut(Timestamp time, Integer movieId, Integer roomId, Integer placeLeft) {
        this.time = time;
        this.movieId = movieId;
        this.roomId = roomId;
        this.placeLeft = placeLeft;
    }
}