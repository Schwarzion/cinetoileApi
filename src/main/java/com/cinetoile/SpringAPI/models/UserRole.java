package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="user_role")
@NoArgsConstructor
public class UserRole {
    @EmbeddedId
    private UserRolePk id;
}
