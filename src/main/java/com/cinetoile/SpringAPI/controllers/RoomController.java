package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.dto.dtoIn.RoomDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.RoomDTOOut;
import com.cinetoile.SpringAPI.models.RoomEntity;
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
    List<RoomDTOOut> all() {
        return this.roomService.findAll();
    }

    @GetMapping("/room/{id}")
    RoomDTOOut one(@PathVariable Integer id) {
        return this.roomService.findDto(id);
    }

    @GetMapping("/room/theater/{theaterId}")
    List<RoomDTOOut> allByTheater(@PathVariable Integer theaterId) {
        return this.roomService.findByAllTheaterId(theaterId);
    }

    @PostMapping("/room/")
    RoomDTOOut add(@RequestBody RoomDTOIn newRoom) {
        return this.roomService.add(newRoom);
    }

    @PutMapping("/room/{id}")
    RoomDTOOut update(@RequestBody RoomDTOIn newRoom, @PathVariable Integer id) {
        return this.roomService.update(newRoom, id);
    }

    @DeleteMapping("/room/{id}")
    void delete(@PathVariable Integer id) {
        this.roomService.delete(id);
    }
}
