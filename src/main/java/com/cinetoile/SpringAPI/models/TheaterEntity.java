package com.cinetoile.SpringAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

@Entity
@Data
@Table(name="Theater")
@NoArgsConstructor
public class TheaterEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "description", nullable = false, length = 160)
    private String description;

    @Basic
    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Basic
    @Column(name = "address", nullable = false, length = 120)
    private String address;

    @Basic
    @Column(name = "city", nullable = false, length = 60)
    private String city;

    @Basic
    @Column(name = "postalCode", nullable = false, length = 5)
    private String postalCode;

    @Basic
    @Column(name = "streetNumber", nullable = false, length = 7)
    private String streetNumber;

    @Basic
    @Column(name = "phoneNumber", nullable = false, length = 11)
    private String phoneNumber;

    @Basic
    @Column(name = "mail", nullable = false, length = 120)
    private String mail;

    @Basic
    @Column(name = "image", nullable = true)
    private byte[] image;

    @Basic
    @Column(name = "createdAt", nullable = false)
    @JsonIgnore
    private Timestamp createdAt;

    @Basic
    @Column(name = "updatedAt", nullable = false)
    @JsonIgnore
    private Timestamp updatedAt;

    public TheaterEntity(String name, String description, String address, String streetNumber, String city, String postalCode, String phoneNumber, String mail, byte[] image) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.streetNumber = streetNumber;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.image = image;
        this.createdAt = new Timestamp(new Date().getTime());
        this.updatedAt = new Timestamp(new Date().getTime());
    }


}
