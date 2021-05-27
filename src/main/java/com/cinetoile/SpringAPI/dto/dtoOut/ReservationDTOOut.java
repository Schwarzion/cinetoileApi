package com.cinetoile.SpringAPI.dto.dtoOut;

import lombok.Data;

@Data
public class ReservationDTOOut {
    private Integer id;
    private Integer status;
    private Integer userId;
    private Integer sessionId;
    private Integer priceId;
    private String name;
    private String lastname;

    public ReservationDTOOut() {
    }

    public ReservationDTOOut(Integer id,Integer status, Integer userId, Integer sessionId, Integer priceId) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.sessionId = sessionId;
        this.priceId = priceId;
    }

    public ReservationDTOOut(Integer id, Integer status, Integer userId, Integer sessionId, Integer priceId, String name, String lastname) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.sessionId = sessionId;
        this.priceId = priceId;
        this.name = name;
        this.lastname = lastname;
    }
}
