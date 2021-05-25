package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.dto.dtoOut.UserDTOOut;
import com.cinetoile.SpringAPI.models.UserEntity;
import com.cinetoile.SpringAPI.dto.dtoIn.LoginDTOIn;
import com.cinetoile.SpringAPI.dto.dtoIn.SignupDTOIn;
import com.cinetoile.SpringAPI.repository.UserRepository;
import com.cinetoile.SpringAPI.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    UserDTOOut getCurrentProfile() {
        return this.userService.findCurrentUser();
    }

    @PostMapping("/signin")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTOIn loginRequest) {
        return this.userService.signin(loginRequest);
    }

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignupDTOIn signUpRequest) {
        return this.userService.signup(signUpRequest);
    }

    @GetMapping("/users")
    List<UserDTOOut> all() {
        return this.userService.findAll();
    }

    @GetMapping("/user/{id}")
    UserDTOOut one(@PathVariable Integer id) {
        return this.userService.findDto(id);
    }

    @PutMapping("/user/{id}")
    UserDTOOut update(@RequestBody UserEntity newUser, @PathVariable Integer id) {
        return this.userService.update(newUser, id);
    }

    @PutMapping("user/{id}/{status}")
    UserDTOOut updateStatus(@PathVariable Integer id, @PathVariable Integer status) {
        return this.userService.updateStatus(id, status);
    }

    @DeleteMapping("/user/{id}")
    void delete(@PathVariable Integer id) {
        this.userService.delete(id);
    }
}
