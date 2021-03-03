package com.cinetoile.SpringAPI.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ReservationPK implements Serializable {

    @Column(name = "userId", nullable = false)
    @Id
    private int userId;

    @Column(name = "sessionId", nullable = false)
    @Id
    private int sessionId;
}
