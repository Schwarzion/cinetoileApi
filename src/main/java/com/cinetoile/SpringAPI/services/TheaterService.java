package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.TheaterDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.TheaterDTOOut;
import com.cinetoile.SpringAPI.models.TheaterEntity;
import com.cinetoile.SpringAPI.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {
    private final TheaterRepository repository;

    TheaterService(TheaterRepository repository) { this.repository = repository; }

    private TheaterDTOOut convertToTheaterDto(TheaterEntity theater) {
        return new TheaterDTOOut(
                theater.getId(),
                theater.getDescription(),
                theater.getName(),
                theater.getAddress(),
                theater.getCity(),
                theater.getPostalCode(),
                theater.getStreetNumber(),
                theater.getPhoneNumber(),
                theater.getMail(),
                theater.getImage()
        );
    }

    private List<TheaterDTOOut> convertToListTheaterDto(List<TheaterEntity> list) {
        return list.stream().map(theater -> new TheaterDTOOut(
                theater.getId(),
                theater.getDescription(),
                theater.getName(),
                theater.getAddress(),
                theater.getCity(),
                theater.getPostalCode(),
                theater.getStreetNumber(),
                theater.getPhoneNumber(),
                theater.getMail(),
                theater.getImage()
        )).collect(Collectors.toList());
    }

    public List<TheaterDTOOut> findAll() { return convertToListTheaterDto(repository.findAll()); }

    public TheaterEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Theater", id.toString()));
    }

    public TheaterDTOOut findDto(Integer id) {
        TheaterEntity theater = repository.findById(id).orElseThrow(() -> new NotFoundException("Theater", id.toString()));
        return convertToTheaterDto(theater);
    }

    public TheaterDTOOut add(TheaterDTOIn newTheater) {
        TheaterEntity theater = new TheaterEntity(
                newTheater.getName(),
                newTheater.getDescription(),
                newTheater.getAddress(),
                newTheater.getStreetNumber(),
                newTheater.getCity(),
                newTheater.getPostalCode(),
                newTheater.getPhoneNumber(),
                newTheater.getMail(),
                newTheater.getImage()
        );
        repository.save(theater);
        return convertToTheaterDto(theater);
    }

    public TheaterDTOOut update(TheaterDTOIn newTheater, Integer id) {
        return repository.findById(id).map(theater -> {
            theater.setAddress(newTheater.getAddress());
            theater.setDescription(newTheater.getDescription());
            theater.setName(newTheater.getName());
            theater.setImage(newTheater.getImage());
            theater.setCity(newTheater.getCity());
            theater.setPostalCode(newTheater.getPostalCode());
            theater.setStreetNumber(newTheater.getStreetNumber());
            theater.setPhoneNumber(newTheater.getPhoneNumber());
            theater.setMail(newTheater.getMail());
            theater.setUpdatedAt(new Timestamp(new Date().getTime()));

            repository.save(theater);

            return convertToTheaterDto(theater);
        }).orElseThrow(() -> new NotFoundException("Theater", id.toString()));
    }

    public void delete(Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
