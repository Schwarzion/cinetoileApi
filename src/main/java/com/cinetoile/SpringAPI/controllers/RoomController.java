package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.dto.In.RoomDTOIn;
import com.cinetoile.SpringAPI.dto.In.TheaterDTOIn;
import com.cinetoile.SpringAPI.dto.Out.RoomDTOOut;
import com.cinetoile.SpringAPI.models.RoomEntity;
import com.cinetoile.SpringAPI.models.TheaterEntity;
import com.cinetoile.SpringAPI.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    private final RoomService roomService;

    @Autowired
    RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/room")
    List<RoomEntity> all() {
        return this.roomService.findAll();
    }

    @GetMapping("/room/{id}")
    RoomEntity one(@PathVariable Integer id) {
        return this.roomService.findById(id);
    }

    @GetMapping("/room/theater/{theaterId}")
    List<RoomEntity> allByTheater(@PathVariable Integer theaterId) {
        return this.roomService.findByAllTheaterId(theaterId);
    }

    @PostMapping("/room/")
    @ResponseStatus(HttpStatus.CREATED)
    RoomDTOOut add(@RequestBody RoomDTOIn newRoom) {
        return this.roomService.add(newRoom);
    }

    @PutMapping("/room/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    RoomEntity update(@RequestBody RoomDTOIn newRoom, @PathVariable Integer id) {
        return this.roomService.update(newRoom, id);
    }

    @DeleteMapping("/room/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        this.roomService.delete(id);
    }
}
