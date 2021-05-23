package com.cinetoile.SpringAPI.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "TheaterRequest", schema = "cinetoile3", catalog = "")
public class TheaterRequestEntity {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Basic
    @Column(name = "address", nullable = false, length = 120)
    private String address;

    @Basic
    @Column(name = "streetNumber", nullable = false, length = 7)
    private String streetNumber;

    @Basic
    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Basic
    @Column(name = "postalCode", nullable = false, length = 5)
    private String postalCode;

    @Basic
    @Column(name = "mail", nullable = false, length = 120)
    private String mail;

    @Basic
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;
}
