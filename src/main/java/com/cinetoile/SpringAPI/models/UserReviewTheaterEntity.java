package com.cinetoile.SpringAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @JsonIgnore
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne(targetEntity = TheaterEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name= "theaterId", nullable = false, referencedColumnName = "id")
    private int theaterId;

    @ManyToOne(targetEntity = UserEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name= "userId", nullable = false, referencedColumnName = "id")
    private int userId;
}
