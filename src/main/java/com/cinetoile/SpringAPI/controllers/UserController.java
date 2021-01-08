package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.models.User;
import com.cinetoile.SpringAPI.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// All data returned is written into the response instead of rendering template
@RestController
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("user", id));
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Integer id) {

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

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Integer id) {
        repository.deleteById(id);
    }

}
