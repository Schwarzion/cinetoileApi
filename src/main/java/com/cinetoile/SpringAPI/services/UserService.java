package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.models.User;
import com.cinetoile.SpringAPI.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    UserService(UserRepository repository) { this.repository = repository;}

    public List<User> findAll() { return repository.findAll();}

    
}

