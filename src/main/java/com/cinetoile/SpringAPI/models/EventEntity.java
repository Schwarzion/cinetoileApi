package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Event")
public class EventEntity {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "startDate", nullable = false)
    private Timestamp startDate;

    @Basic
    @Column(name = "endDate", nullable = false)
    private Timestamp endDate;

    @Basic
    @Column(name = "status", nullable = false)
    private int status;

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;


    @Basic
    @Column(name = "description", nullable = false, length = 300)
    private String description;


    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;


    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;


    @Basic
    @Column(name = "theaterId", nullable = false)
    private int theaterId;
}
