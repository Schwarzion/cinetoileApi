package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.In.RoomDTOIn;
import com.cinetoile.SpringAPI.dto.Out.RoomDTOOut;
import com.cinetoile.SpringAPI.models.RoomEntity;
import com.cinetoile.SpringAPI.models.TheaterEntity;
import com.cinetoile.SpringAPI.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository repository;

    private final TheaterService theaterService;

    RoomService(RoomRepository repository, TheaterService theaterService) {
        this.repository = repository;
        this.theaterService = theaterService;
    }

    public List<RoomEntity> findAll() { return repository.findAll(); }

    public List<RoomEntity> findByAllTheaterId(Integer id) { return repository.findAllByTheaterId(id); }

    public RoomEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Room", id.toString()));
    }

    public RoomDTOOut add(RoomDTOIn newRoom) {
        TheaterEntity theater = theaterService.findById(newRoom.getTheaterId());
        RoomEntity room = new RoomEntity(
                newRoom.getName(),
                newRoom.getPlace(),
                theater
        );
        RoomEntity savedRoom = repository.save(room);
        return new RoomDTOOut(
                savedRoom.getId(),
                savedRoom.getName(),
                savedRoom.getPlace(),
                savedRoom.getTheater()
        );
    }


    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    public RoomEntity update(RoomDTOIn newRoom, Integer id) {
        return repository.findById(id).map(room -> {
            room.setName(newRoom.getName());
            room.setPlace(newRoom.getPlace());
            room.setTheater(theaterService.findById(newRoom.getTheaterId()));
            room.setUpdatedAt(new Timestamp(new Date().getTime()));

            return repository.save(room);
        }).orElseThrow(() -> new NotFoundException("Room", id.toString()));
    }

}
