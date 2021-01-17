package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.models.User;
import com.cinetoile.SpringAPI.repository.UserRepository;
import com.cinetoile.SpringAPI.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// All data returned is written into the response instead of rendering template
@RestController
public class UserController {

    private final UserService service;

    UserController(UserRepository repository, UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    List<User> all() {
        return service.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return service.addUser(newUser);
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Integer id) {

        return service.replaceUser(newUser, id);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Integer id) {
        service.deleteUser(id);
    }

}
