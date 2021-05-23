package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.BlobType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@Table(name="User")
@NoArgsConstructor
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

    @OneToMany(mappedBy = "userId", targetEntity = UserReviewMovieEntity.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<UserReviewMovieEntity> moviesReviewed;

    public UserEntity(
            String firstname,
            String lastname,
            String city,
            String postalCode,
            Timestamp birthdate,
            int status,
            String phone,
            String mail,
            String password
    ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.postalCode = postalCode;
        this.birthdate = birthdate;
        this.status = status;
        this.phone = phone;
        this.mail = mail;
        this.password = password;
    }

   /* @OneToMany(mappedBy = "id.user", targetEntity = UserRole.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<UserRole> userRoles;*/
}
