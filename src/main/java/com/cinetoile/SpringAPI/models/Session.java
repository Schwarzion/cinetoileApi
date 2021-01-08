package com.cinetoile.SpringAPI.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Session {
    private int id;
    private Timestamp time;
    private int placeLeft;
    private int roomId;
    private int theaterId;
    private int movieId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "placeLeft", nullable = false)
    public int getPlaceLeft() {
        return placeLeft;
    }

    public void setPlaceLeft(int placeLeft) {
        this.placeLeft = placeLeft;
    }

    @Basic
    @Column(name = "roomId", nullable = false)
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "theaterId", nullable = false)
    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    @Basic
    @Column(name = "movieId", nullable = false)
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Basic
    @Column(name = "createdAt", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updatedAt", nullable = false)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id &&
                placeLeft == session.placeLeft &&
                roomId == session.roomId &&
                theaterId == session.theaterId &&
                movieId == session.movieId &&
                Objects.equals(time, session.time) &&
                Objects.equals(createdAt, session.createdAt) &&
                Objects.equals(updatedAt, session.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, placeLeft, roomId, theaterId, movieId, createdAt, updatedAt);
    }
}
