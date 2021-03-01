package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.Dto.UserDTO;
import com.cinetoile.SpringAPI.models.User;
import com.cinetoile.SpringAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

// All data returned is written into the response instead of rendering template
@RestController
public class UserController {

    private final UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userService.add(user);
    }

}
