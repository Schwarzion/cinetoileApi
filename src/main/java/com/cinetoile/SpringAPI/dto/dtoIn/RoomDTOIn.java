package com.cinetoile.SpringAPI.dto.dtoIn;

import com.cinetoile.SpringAPI.models.TheaterEntity;
import lombok.Data;

@Data
public class RoomDTOIn {
    private String name;
    private int place;
    private Integer theaterId;

    public RoomDTOIn(String name, int place, Integer theaterId) {
        this.name = name;
        this.place = place;
        this.theaterId = theaterId;
    }
}
