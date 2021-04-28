package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.models.PricingEntity;
import com.cinetoile.SpringAPI.repository.PricingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {

    private final PricingRepository repository;

    PricingService(PricingRepository repository) { this.repository = repository;}

    public List<PricingEntity> findAll() { return repository.findAll();}

    public PricingEntity find(Integer id) { return repository.findById(id).orElseThrow(() -> new NotFoundException("category ", id.toString())); }

    public PricingEntity add(PricingEntity newPrice) {
        return repository.save(newPrice);
    }

    public PricingEntity update(PricingEntity newPrice, Integer id) {
        return repository.findById(id).map(price -> {
            price.setName(newPrice.getName());
            return repository.save(price);
        }).orElseGet(() -> {
            newPrice.setId(id);
            return repository.save(newPrice);
        });
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
