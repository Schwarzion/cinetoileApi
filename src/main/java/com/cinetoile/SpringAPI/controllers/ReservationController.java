package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.models.Reservation;
import com.cinetoile.SpringAPI.repository.ReservationRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class ReservationController {

    private final ReservationRepository repository;


    public ReservationController(ReservationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/reservations")
    List<Reservation> all() { return repository.findAll(); }

    @GetMapping("/reservations/{id}/{}/{}")
    Reservation one(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("reservation", id));
    }
}
