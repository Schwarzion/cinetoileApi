package com.cinetoile.SpringAPI.dto.dtoIn;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class SignupDTOIn {
    @NotBlank
    @Size(min = 3, max = 40)
    private String firstname;

    @NotBlank
    @Size(min = 3, max = 40)
    private String lastname;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private List<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    private Integer status;

    public SignupDTOIn(String firstname, String lastname, String username, String email, List<String> role, String password,
                       Integer status) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
        this.status = status;
    }
}
