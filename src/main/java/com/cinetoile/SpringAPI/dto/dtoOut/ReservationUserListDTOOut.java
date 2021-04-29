package com.cinetoile.SpringAPI.dto.dtoOut;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class ReservationUserListDTOOut {
    private Integer id;
    private Integer status;
    private BigDecimal price;
    private String priceName;
    private String theaterName;
    private String movieName;
    private Timestamp date;


    public ReservationUserListDTOOut(Integer id, Integer status, BigDecimal price, String priceName, String theaterName, String movieName, Timestamp date) {
        this.id = id;
        this.status = status;
        this.price = price;
        this.priceName = priceName;
        this.theaterName = theaterName;
        this.movieName = movieName;
        this.date = date;
    }
}
