package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.BlobType;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="User")
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "firstname", nullable = false, length = 60)
    private String firstname;
    @Basic
    @Column(name = "lastname", nullable = false, length = 60)
    private String lastname;
    @Basic
    @Column(name = "city", nullable = false, length = 60)
    private String city;
    @Basic
    @Column(name = "postalCode", nullable = false, length = 5)
    private String postalCode;
    @Basic
    @Column(name = "birthdate", nullable = false)
    private Timestamp birthdate;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @Basic
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;
    @Basic
    @Column(name = "mail", nullable = false, length = 120)
    private String mail;
    @Basic
    @Column(name = "password", nullable = false, length = 64)
    private String password;
    @Basic
    @Column(name = "image", nullable = true)
    private BlobType image;
    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;
    @Basic
    @Column(name="theaterId", nullable = true)
    private Integer theaterId;

    @OneToMany(mappedBy = "id.user", targetEntity = UserReviewMovie.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<UserReviewMovie> moviesReviewed;

    @OneToMany(mappedBy = "id.user", targetEntity = UserRole.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<UserRole> userRoles;

}
