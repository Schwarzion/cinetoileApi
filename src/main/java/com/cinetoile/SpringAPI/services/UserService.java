package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.models.UserEntity;
import com.cinetoile.SpringAPI.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    UserService(UserRepository repository) { this.repository = repository;}

    public List<UserEntity> findAll() { return repository.findAll();}

    public UserEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("user", id.toString()));
    }

    public UserEntity add(UserEntity newUser) { return repository.save(newUser);}

    public UserEntity update(UserEntity newUser, Integer id) {
        return repository.findById(id).map(user -> {
            user.setBirthdate(newUser.getBirthdate());
            user.setFirstname(newUser.getFirstname());
            user.setLastname(newUser.getLastname());
            user.setCity(newUser.getCity());
            user.setImage(newUser.getImage());
            user.setMail(newUser.getMail());
            user.setPassword(newUser.getPassword());
            user.setImage(newUser.getImage());
            user.setPhone(newUser.getPhone());
            user.setPostalCode(newUser.getPostalCode());
            user.setImage(newUser.getImage());

            return repository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            return repository.save(newUser);
        });
    }

    public void delete(Integer id) { repository.deleteById(id);}
}

