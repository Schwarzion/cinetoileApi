package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.models.User;
import com.cinetoile.SpringAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// All data returned is written into the response instead of rendering template
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    List<User> all() {
        return this.userService.findAll();
    }

    @GetMapping("/user/{id}")
    User one(@PathVariable Integer id) {
        return this.userService.findById(id);
    }

    @PostMapping("/user")
    User add(@RequestBody User newUser) {
        return this.userService.add(newUser);
    }

    @PutMapping("/user/{id}")
    User update(@RequestBody User newUser, @PathVariable Integer id) {
        return this.userService.update(newUser, id);
    }

    @DeleteMapping("/user/{id}")
    void delete(@PathVariable Integer id) {
        this.userService.delete(id);
    }

}
