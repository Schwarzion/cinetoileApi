package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.SessionDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.ReservationDTOOut;
import com.cinetoile.SpringAPI.dto.dtoOut.SessionDTOOut;
import com.cinetoile.SpringAPI.exceptions.BadRequestException;
import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.models.RoomEntity;
import com.cinetoile.SpringAPI.models.SessionEntity;
import com.cinetoile.SpringAPI.repository.MovieRepository;
import com.cinetoile.SpringAPI.repository.RoomRepository;
import com.cinetoile.SpringAPI.repository.SessionRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionService {

    private final SessionRepository repository;
    private final RoomRepository roomRepository;
    private final MovieRepository movieRepository;

    SessionService(SessionRepository repository, RoomRepository roomRepository, MovieRepository movieRepository) {
        this.repository = repository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }

    public List<SessionDTOOut> findAll() {
        List<SessionEntity> list = repository.findAll();
        return list.stream()
                .map(session -> new SessionDTOOut(session.getTime(), session.getMovieId().getId(), session.getRoomId().getId()))
                .collect(Collectors.toList());
    }

    public SessionEntity find(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("session ", id.toString()));
    }

    public SessionDTOOut findDto(Integer id) {
        SessionEntity session = repository.findById(id).orElseThrow(() -> new NotFoundException("session ", id.toString()));
        return new SessionDTOOut(session.getTime(), session.getMovieId().getId(), session.getRoomId().getId(), session.getPlaceLeft());
    }

    public SessionEntity add(SessionEntity newSession) {
        return repository.save(newSession);
    }

    public SessionDTOOut update(SessionDTOIn newSession, Integer id) {
        MovieEntity movie = this.movieRepository.findById(newSession.getMovieId()).orElseThrow(() -> new NotFoundException("movie ", newSession.getMovieId().toString()));
        RoomEntity room = this.roomRepository.findById(newSession.getRoomId()).orElseThrow(() -> new NotFoundException("room ", newSession.getRoomId().toString()));
        return repository.findById(id).map(session -> {
            session.setTime(newSession.getTime());
            session.setMovieId(movie);
            session.setRoomId(room);
            session.setCreatedAt(new Timestamp(new Date().getTime()));
            session.setUpdatedAt(new Timestamp(new Date().getTime()));
            session = repository.save(session);
            return convertToSessionDto(session);
        }).orElseThrow(() -> new NotFoundException("session ", id.toString()));
    }

    private SessionDTOOut convertToSessionDto(SessionEntity session) {
        return new SessionDTOOut()
    }

    private Integer updatePlaceLeft(Integer sessionId, Integer typeOfUpdate) throws BadRequestException {
        if (typeOfUpdate == 1) {
            return 149;
        } else if (typeOfUpdate == 2) {
            return 151;
        } else {
            throw new BadRequestException();
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

