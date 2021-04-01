package com.cinetoile.SpringAPI.dto.Out;

import lombok.Data;

@Data
public class TheaterDTOOut {

    private Integer id;
    private String description;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private String streetNumber;
    private String phoneNumber;
    private String mail;
    private byte[] image;

    public TheaterDTOOut(Integer id, String description, String name, String address, String city, String postalCode, String streetNumber, String phoneNumber, String mail, byte[] image) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.streetNumber = streetNumber;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.image = image;
    }
}
