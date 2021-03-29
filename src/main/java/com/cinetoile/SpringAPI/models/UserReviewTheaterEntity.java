package com.cinetoile.SpringAPI.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "User_Review_Theater")
public class UserReviewTheaterEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Basic
    @Column(name = "comment", nullable = false, length = 200)
    private String comment;

    @Basic
    @Column(name = "rate", nullable = false)
    private int rate;

    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    @Basic
    @Column(name = "theaterId", nullable = false)
    private int theaterId;

    @Basic
    @Column(name = "userId", nullable = false)
    private int userId;
}
