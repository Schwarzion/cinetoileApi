package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.ERole;
import com.cinetoile.SpringAPI.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(ERole name);
}
