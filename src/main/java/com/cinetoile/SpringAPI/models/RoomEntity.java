package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Room")
public class RoomEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false, length = 12)
    private String name;

    @Basic
    @Column(name = "place", nullable = false)
    private Integer place;

    @ManyToOne(targetEntity = TheaterEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "theaterId", nullable = false, referencedColumnName = "id")
    private TheaterEntity theater;

    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    public RoomEntity(String name, int place, TheaterEntity theater) {
        this.name = name;
        this.place = place;
        this.theater = theater;
        this.createdAt = new Timestamp(new Date().getTime());
        this.updatedAt = new Timestamp(new Date().getTime());
    }
}
