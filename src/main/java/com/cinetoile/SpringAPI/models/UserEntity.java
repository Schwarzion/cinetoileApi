package com.cinetoile.SpringAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "User")
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "firstname", nullable = false, length = 60)
    private String firstname;

    @Basic
    @Column(name = "lastname", nullable = false, length = 60)
    private String lastname;

    @Basic
    @Column(name = "city", nullable = true, length = 60)
    private String city;

    @Basic
    @Column(name = "postalCode", nullable = true, length = 5)
    private String postalCode;

    @Basic
    @Column(name = "birthdate", nullable = true)
    private Timestamp birthdate;

    @Basic
    @Column(name = "status", nullable = false)
    private Integer status;

    @Basic
    @Column(name = "phone", nullable = true, length = 11)
    private String phone;

    @Basic
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Basic
    @Column(name = "email", nullable = false, length = 120)
    private String email;

    @Basic
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Basic
    @Column(name = "image", nullable = true, length = 100)
    private String image;

    @Basic
    @JsonIgnore
    @CreationTimestamp
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Basic
    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId", nullable = true))
    private List<RoleEntity> roles = new ArrayList<RoleEntity>();

    public UserEntity() {
    }

    public UserEntity(String username, String lastname, String firstname, String email, String password,
                      Integer status) {
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.status = status;
    }
}