package com.cinetoile.SpringAPI.dto.In;

import lombok.Data;

@Data
public class UserReviewTheaterDTOIn {
    private Integer id;
    private Integer theaterId;
    private Integer userId;
    private String title;
    private String comment;
    private int rate;

    public UserReviewTheaterDTOIn(Integer id,Integer theaterId, Integer userId, String title, String comment, int rate) {
        this.id = id;
        this.theaterId = theaterId;
        this.userId = userId;
        this.title = title;
        this.comment = comment;
        this.rate = rate;
    }
}
