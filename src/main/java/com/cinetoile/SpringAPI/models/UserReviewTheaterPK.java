package com.cinetoile.SpringAPI.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserReviewTheaterPK implements Serializable {
    private int theaterId;
    private int userId;

    @Column(name = "theaterId", nullable = false)
    @Id
    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    @Column(name = "userId", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserReviewTheaterPK that = (UserReviewTheaterPK) o;
        return theaterId == that.theaterId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(theaterId, userId);
    }
}
