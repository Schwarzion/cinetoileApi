package com.cinetoile.SpringAPI.controllers;


import com.cinetoile.SpringAPI.dto.dtoIn.SessionDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.SessionDTOOut;
import com.cinetoile.SpringAPI.models.RoomEntity;
import com.cinetoile.SpringAPI.models.SessionEntity;
import com.cinetoile.SpringAPI.services.SessionService;
import com.cinetoile.SpringAPI.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SessionController {
    private final SessionService sessionService;
    private final RoomService roomService;

    @Autowired
    public SessionController(SessionService sessionService, RoomService roomService) {
        this.sessionService = sessionService;
        this.roomService = roomService;
    }

    @GetMapping("/sessions")
    List<SessionDTOOut> all() {
        return this.sessionService.findAll();
    }

    @GetMapping("/session/{id}")
    SessionDTOOut one(@PathVariable Integer id) {
        return this.sessionService.findDto(id);
    }

    @PostMapping("/session")
    SessionEntity add(@RequestBody SessionEntity newSession) {
        //newSession.setRoom(roomService.find(newSession.getRoom()));
        return this.sessionService.add(newSession);
    }

    @PutMapping("session/{id}")
    SessionEntity update(@RequestBody SessionDTOIn newSession, @PathVariable Integer id) {
        return this.sessionService.update(newSession, id);
    }

    @GetMapping("/session/room/{id}")
    RoomEntity room(@PathVariable Integer id) {
        SessionEntity session = this.sessionService.find(id);
        return session.getRoomId();
    }
}
