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
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "time", nullable = false)
    private Timestamp time;

    @Basic
    @Column(name = "placeLeft", nullable = false)
    private Integer placeLeft;

    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId", referencedColumnName = "id")
    private MovieEntity movieId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId", referencedColumnName = "id")
    private RoomEntity roomId;
}
