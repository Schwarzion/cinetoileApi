package com.cinetoile.SpringAPI.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@IdClass(TheaterPK.class)
public class Theater {
    private int id;
    private String description;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private String streetNumber;
    private String phoneNumber;
    private String mail;
    private byte[] image;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int adminId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 160)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 120)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 60)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "postalCode", nullable = false, length = 5)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "streetNumber", nullable = false, length = 7)
    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Basic
    @Column(name = "phoneNumber", nullable = false, length = 11)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "mail", nullable = false, length = 120)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "image", nullable = true)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Basic
    @Column(name = "createdAt", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updatedAt", nullable = false)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Id
    @Column(name = "adminId", nullable = false)
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theater theater = (Theater) o;
        return id == theater.id &&
                adminId == theater.adminId &&
                Objects.equals(description, theater.description) &&
                Objects.equals(name, theater.name) &&
                Objects.equals(address, theater.address) &&
                Objects.equals(city, theater.city) &&
                Objects.equals(postalCode, theater.postalCode) &&
                Objects.equals(streetNumber, theater.streetNumber) &&
                Objects.equals(phoneNumber, theater.phoneNumber) &&
                Objects.equals(mail, theater.mail) &&
                Arrays.equals(image, theater.image) &&
                Objects.equals(createdAt, theater.createdAt) &&
                Objects.equals(updatedAt, theater.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, description, name, address, city, postalCode, streetNumber, phoneNumber, mail, createdAt, updatedAt, adminId);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
