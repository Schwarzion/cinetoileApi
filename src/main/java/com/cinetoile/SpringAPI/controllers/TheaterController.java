package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.dto.dtoIn.TheaterDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.TheaterDTOOut;
import com.cinetoile.SpringAPI.models.TheaterEntity;
import com.cinetoile.SpringAPI.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheaterController {

    private final TheaterService theaterService;

    @Autowired
    TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping("/theater")
    List<TheaterDTOOut> all() {
        return this.theaterService.findAll();
    }

    @GetMapping("/theater/{id}")
    TheaterDTOOut one(@PathVariable Integer id) {
        return this.theaterService.findDto(id);
    }

    @PostMapping("/theater")
    TheaterDTOOut add(@RequestBody TheaterDTOIn newTheater) {
        return this.theaterService.add(newTheater);
    }

    @PutMapping("/theater/{id}")
    TheaterDTOOut update(@RequestBody TheaterDTOIn newTheater, @PathVariable Integer id) {
        return this.theaterService.update(newTheater, id);
    }

    @DeleteMapping("/theater/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        this.theaterService.delete(id);
    }

}
