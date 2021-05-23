package com.cinetoile.SpringAPI.dto.dtoIn;

import lombok.Data;

@Data
public class TheaterDTOIn {

    private String description;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private String streetNumber;
    private String phoneNumber;
    private String mail;
    private byte[] image;

    public TheaterDTOIn(String name, String description, String address, String streetNumber, String city, String postalCode,  String phoneNumber, String mail, byte[] image) {
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
