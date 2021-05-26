package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.RoomDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.RoomDTOOut;
import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.models.RoomEntity;
import com.cinetoile.SpringAPI.models.TheaterEntity;
import com.cinetoile.SpringAPI.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository repository;

    private final TheaterService theaterService;

    RoomService(RoomRepository repository, TheaterService theaterService) {
        this.repository = repository;
        this.theaterService = theaterService;
    }

    private RoomDTOOut convertToRoomDto(RoomEntity room) {
        return new RoomDTOOut(
                room.getId(),
                room.getName(),
                room.getPlace()
        );
    }

    private List<RoomDTOOut> convertToListRoomDto(List<RoomEntity> list) {
        return list.stream().map(room -> new RoomDTOOut(
                room.getId(),
                room.getName(),
                room.getPlace()
        )).collect(Collectors.toList());
    }

    public List<RoomDTOOut> findAll() { return convertToListRoomDto(repository.findAll()); }

    public List<RoomDTOOut> findByAllTheaterId(Integer id) { return convertToListRoomDto(repository.findAllByTheaterId(id)); }

    public RoomEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Room", id.toString()));
    }

    public RoomDTOOut findDto(Integer id) {
        RoomEntity room = repository.findById(id).orElseThrow(() -> new NotFoundException("room", id.toString()));
        return convertToRoomDto(room);
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
                savedRoom.getPlace()
        );
    }


    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    public RoomDTOOut update(RoomDTOIn newRoom, Integer id) {
        return repository.findById(id).map(room -> {
            room.setName(newRoom.getName());
            room.setPlace(newRoom.getPlace());
            room.setTheater(theaterService.findById(newRoom.getTheaterId()));
            //room.setUpdatedAt(new Timestamp(new Date().getTime()));
            repository.save(room);
            return convertToRoomDto(room);
        }).orElseThrow(() -> new NotFoundException("Room", id.toString()));
    }
}