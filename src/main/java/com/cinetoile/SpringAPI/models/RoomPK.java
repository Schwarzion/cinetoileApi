package com.cinetoile.SpringAPI.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RoomPK implements Serializable {
    private int id;
    private int theaterId;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "theaterId", nullable = false)
    @Id
    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomPK roomPK = (RoomPK) o;
        return id == roomPK.id &&
                theaterId == roomPK.theaterId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, theaterId);
    }
}
