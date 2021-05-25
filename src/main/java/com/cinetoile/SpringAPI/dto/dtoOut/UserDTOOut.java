package com.cinetoile.SpringAPI.dto.dtoOut;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class UserDTOOut {
    Integer id;
    String firstname;
    String lastname;
    String city;
    String postalCode;
    Timestamp birthdate;
    Integer status;
    String phone;
    String username;
    String email;
    String image;

    public UserDTOOut(Integer id, String firstname, String lastname, String city, String postalCode,
                      Timestamp birthdate, Integer status, String phone, String username, String email,
                      String image) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.postalCode = postalCode;
        this.birthdate = birthdate;
        this.status = status;
        this.phone = phone;
        this.username = username;
        this.email = email;
        this.image = image;
    }
}
