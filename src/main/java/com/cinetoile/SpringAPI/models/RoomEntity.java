package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Room", schema = "cinetoile3", catalog = "")
public class RoomEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 12)
    private String name;

    @Basic
    @Column(name = "place", nullable = false)
    private int place;

    @Basic
    @Column(name = "theaterId", nullable = false)
    private int theaterId;


    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;
}
