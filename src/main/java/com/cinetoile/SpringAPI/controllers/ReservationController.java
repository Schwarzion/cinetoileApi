package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.dto.dtoIn.ReservationDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.ReservationDTOOut;
import com.cinetoile.SpringAPI.dto.dtoOut.ReservationUserListDTOOut;
import com.cinetoile.SpringAPI.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    List<ReservationDTOOut> all() { return this.reservationService.findAll(); }

    @GetMapping("/reservations/session/{id}")
    List<ReservationDTOOut> allBySession(@PathVariable Integer id) { return this.reservationService.findAllBySession(id); }

    @GetMapping("/reservations/user/{id}")
    List<ReservationUserListDTOOut> allByUser(@PathVariable Integer id) { return this.reservationService.findAllByUser(id); }

    @GetMapping("/reservations/passed/user/{id}")
    List<ReservationUserListDTOOut> allPassedByUser(@PathVariable Integer id) { return this.reservationService.findAllPassedByUser(id); }

    @GetMapping("/reservations/incoming/user/{id}")
    List<ReservationUserListDTOOut> allIncomingByUser(@PathVariable Integer id) { return this.reservationService.findAllIncomingByUser(id); }

    @GetMapping("/reservation/{id}")
    ReservationDTOOut one(@PathVariable Integer id) {
        return this.reservationService.find(id);
    }

    @PostMapping("/reservation")
    ReservationDTOOut add(@RequestBody ReservationDTOIn newReservation) {
        return this.reservationService.add(newReservation);
    }

    @PutMapping("reservation/{id}/{status}")
    ReservationDTOOut updateStatus(@PathVariable Integer id, @PathVariable Integer status) {
        return this.reservationService.updateStatus(id, status);
    }
}
