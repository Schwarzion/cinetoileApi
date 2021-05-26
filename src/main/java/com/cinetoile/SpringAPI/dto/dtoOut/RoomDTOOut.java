package com.cinetoile.SpringAPI.dto.dtoOut;

import com.cinetoile.SpringAPI.models.TheaterEntity;
import lombok.Data;

@Data
public class RoomDTOOut {
    private Integer id;
    private String name;
    private int place;

    public RoomDTOOut(Integer id, String name, int place) {
        this.id = id;
        this.name = name;
        this.place = place;
    }

    public RoomDTOOut() {}
}
