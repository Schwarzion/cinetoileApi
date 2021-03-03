package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class UserRolePk implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="roleId", nullable = false, insertable = false, updatable = false)
    private Role role;

    public UserRolePk(User user, Role role) {
        this.user = user;
        this.role = role;
    }
}
