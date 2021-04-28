package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.models.PricingEntity;
import com.cinetoile.SpringAPI.services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PricingController {
    private final PricingService pricingService;

    @Autowired
    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping("/price")
    List<PricingEntity> all() {
        return this.pricingService.findAll();
    }

    @GetMapping("/price/{id}")
    PricingEntity one(@PathVariable Integer id) {
        return this.pricingService.find(id);
    }

    @PostMapping("/price/")
    PricingEntity add(@RequestBody PricingEntity newPrice) {
        return this.pricingService.add(newPrice);
    }

    @PutMapping("/price/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    PricingEntity update(@RequestBody PricingEntity newPrice, @PathVariable Integer id) {
        return this.pricingService.update(newPrice, id);
    }

    @DeleteMapping("/price/{id}")
    void delete(@PathVariable Integer id) {
        this.pricingService.delete(id);
    }
}
