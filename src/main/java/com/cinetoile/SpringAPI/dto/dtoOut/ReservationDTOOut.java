package com.cinetoile.SpringAPI.dto.dtoOut;

import lombok.Data;

@Data
public class ReservationDTOOut {
    private Integer id;
    private Integer status;
    private Integer userId;
    private Integer sessionId;
    private Integer priceId;


    public ReservationDTOOut() {
    }

    public ReservationDTOOut(Integer id,Integer status, Integer userId, Integer sessionId, Integer priceId) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.sessionId = sessionId;
        this.priceId = priceId;
    }
}
