package com.cinetoile.SpringAPI.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "Reservation")
public class ReservationEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "status", nullable = false)
    private int status;

    @Basic
    @Column(name = "userId", nullable = false)
    private int userId;

    @Basic
    @Column(name = "sessionId", nullable = false)
    private int sessionId;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;
}
