package com.cinetoile.SpringAPI.dto.dtoOut;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SessionTheaterDTOOut {
    private Integer id;
    private Timestamp time;
    private String movie;
    private String room;
    private Integer reservationNumber;

    public SessionTheaterDTOOut() {
    }

    public SessionTheaterDTOOut(Integer id, Timestamp time, String movie, String room, Integer reservationNumber) {
        this.id = id;
        this.time = time;
        this.movie = movie;
        this.room = room;
        this.reservationNumber = reservationNumber;
    }
}