package com.cinetoile.SpringAPI.dto.dtoIn;

import lombok.Data;

@Data
public class ReservationDTOIn {
    private Integer status;
    private Integer userId;
    private Integer sessionId;
    private Integer priceId;

    public ReservationDTOIn() {
    }

    public ReservationDTOIn(Integer userId, Integer sessionId, Integer priceId) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.priceId = priceId;
    }
}   