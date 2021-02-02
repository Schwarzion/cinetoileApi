package com.cinetoile.SpringAPI.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ReservationPK implements Serializable {
    private int userId;
    private int sessionId;

    @Column(name = "userId", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "sessionId", nullable = false)
    @Id
    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationPK that = (ReservationPK) o;
        return userId == that.userId &&
                sessionId == that.sessionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, sessionId);
    }
}
