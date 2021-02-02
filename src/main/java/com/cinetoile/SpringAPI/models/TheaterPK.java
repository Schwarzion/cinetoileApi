package com.cinetoile.SpringAPI.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TheaterPK implements Serializable {
    private int id;
    private int adminId;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "adminId", nullable = false)
    @Id
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TheaterPK theaterPK = (TheaterPK) o;
        return id == theaterPK.id &&
                adminId == theaterPK.adminId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adminId);
    }
}
