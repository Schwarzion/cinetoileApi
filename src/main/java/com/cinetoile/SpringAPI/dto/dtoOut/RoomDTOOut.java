package com.cinetoile.SpringAPI.dto.dtoOut;

import com.cinetoile.SpringAPI.models.TheaterEntity;
import lombok.Data;

@Data
public class RoomDTOOut {
    private Integer id;
    private String name;
    private int place;
    private TheaterEntity theater;

    public RoomDTOOut(Integer id, String name, int place, TheaterEntity theater) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.theater = theater;
    }

    public RoomDTOOut() {}
}
