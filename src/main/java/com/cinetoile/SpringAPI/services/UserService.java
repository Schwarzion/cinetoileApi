package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.models.User;
import com.cinetoile.SpringAPI.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDetailsServiceImpl userDetailsServiceImpl;

    UserService(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<User> findAll() { return repository.findAll();}

    public User findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("user", id));
    }

    public UserDetails findByMail(String mail) {
        return userDetailsServiceImpl.loadUserByUsername(mail);
    }

    public User add(User newUser) { return repository.save(newUser);}

    public User update(User newUser, Integer id) {
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

