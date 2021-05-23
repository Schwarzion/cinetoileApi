package com.cinetoile.SpringAPI.controllers;


import com.cinetoile.SpringAPI.dto.dtoIn.SessionDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.SessionDTOOut;
import com.cinetoile.SpringAPI.models.RoomEntity;
import com.cinetoile.SpringAPI.models.SessionEntity;
import com.cinetoile.SpringAPI.services.SessionService;
import com.cinetoile.SpringAPI.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class SessionController {
    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService, RoomService roomService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/sessions")
    List<SessionDTOOut> all() {
        return this.sessionService.findAll();
    }

    @GetMapping("/sessions/movie/{id}")
    List<SessionDTOOut> allByMovie(@PathVariable Integer id) {
        return this.sessionService.findByMovie(id);
    }

    @GetMapping("/sessions/movie/{id}/date")
    List<SessionDTOOut> AllByMovieByDAte(@PathVariable Integer id, @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
        return this.sessionService.findByMovieFromDAte(id, fromDate);
    }

    @GetMapping("/session/{id}")
    SessionDTOOut one(@PathVariable Integer id) {
        return this.sessionService.findDto(id);
    }

    @PostMapping("/session")
    SessionDTOOut add(@RequestBody SessionDTOIn newSession) {
        return this.sessionService.add(newSession);
    }

    @PutMapping("session/{id}")
    SessionDTOOut update(@RequestBody SessionDTOIn newSession, @PathVariable Integer id) {
        return this.sessionService.update(newSession, id);
    }

    @PutMapping("session/{id}/place/{update}")
    SessionDTOOut updatePlace(@PathVariable Integer id, @PathVariable Integer update) {
        return sessionService.updatePlaceLeft(id, update);
    }

    @GetMapping("/session/{id}/room")
    RoomEntity room(@PathVariable Integer id) {
        SessionEntity session = this.sessionService.find(id);
        return session.getRoomId();
    }
}
