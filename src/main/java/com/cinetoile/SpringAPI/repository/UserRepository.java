package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByMail(String mail);
}
