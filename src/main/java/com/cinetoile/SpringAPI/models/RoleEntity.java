package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Role")
@NoArgsConstructor
public class RoleEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "description", nullable = false)
    private String description;

    /*@OneToMany(mappedBy = "id.user", targetEntity = UserRole.class,
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<UserRole> userRoles;*/
}