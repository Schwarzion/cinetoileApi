package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Session")
public class SessionEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "time", nullable = false)
    private Timestamp time;

    @Basic
    @Column(name = "placeLeft", nullable = false)
    private int placeLeft;

    @Basic
    @Column(name = "movieId", nullable = false)
    private int movieId;

    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    @Basic
    @Column(name = "roomId", nullable = false)
    private int roomId;
}
