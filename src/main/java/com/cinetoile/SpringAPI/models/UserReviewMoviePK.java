package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
@NoArgsConstructor
public class UserReviewMoviePK implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movieId", nullable = false, insertable = false, updatable = false)
    private Movie movie;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId", nullable = false, insertable = false, updatable = false)
    private User user;

    public UserReviewMoviePK(Movie movie, User user) {
        this.movie = movie;
        this.user = user;
    }

}
