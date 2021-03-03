package com.cinetoile.SpringAPI.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@IdClass(ReservationPK.class)
public class Reservation {
    @Basic
    @Column(name = "status", nullable = false)
    private int status;

    @Id
    @Column(name = "userId", nullable = false)
    private int userId;

    @Id
    @Column(name = "sessionId", nullable = false)
    private int sessionId;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

}
