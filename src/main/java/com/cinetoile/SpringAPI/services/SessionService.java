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
import java.util.Collections;
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

    /**
     * Find all sessions
     *
     * @return SessionDTOOut
     */
    public List<SessionDTOOut> findAll() {
        List<SessionEntity> list = repository.findAll();
        return list.stream()
                .map(this::convertToSessionDto)
                .collect(Collectors.toList());
    }

    /**
     * Find Session
     *
     * @param id
     * @return SessionDTOOut
     */
    public SessionDTOOut findDto(Integer id) {
        SessionEntity session = repository.findById(id).orElseThrow(() -> new NotFoundException("session ", id.toString()));
        return convertToSessionDto(session);
    }

    /**
     * Find Session
     *
     * @param id
     * @return SessionDTOOut
     */
    public List<SessionDTOOut> findByMovie(Integer id) {
        MovieEntity movie = movieRepository.findById(id).orElseThrow(() -> new NotFoundException("movie ", id.toString()));
        List<SessionEntity> list = repository.findByMovieId(movie);
        return list.stream()
                .map(this::convertToSessionDto)
                .collect(Collectors.toList());
    }

    /**
     * Find Session - only used in logic, not returned in Requests
     *
     * @param id
     * @return SessionEntity
     */
    public SessionEntity find(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("session ", id.toString()));
    }

    /**
     * Add session
     *
     * @param newSession
     * @return SessionDTOOut
     */
    public SessionDTOOut add(SessionDTOIn newSession) {
        MovieEntity movie = this.movieRepository.findById(newSession.getMovieId()).orElseThrow(() -> new NotFoundException("movie ", newSession.getMovieId().toString()));
        RoomEntity room = this.roomRepository.findById(newSession.getRoomId()).orElseThrow(() -> new NotFoundException("room ", newSession.getRoomId().toString()));
        SessionEntity session = new SessionEntity(room, movie, newSession.getTime());
        session = repository.save(session);
        return convertToSessionDto(session);
    }

    /**
     * Update session
     *
     * @param newSession
     * @param id
     * @return SessionDTOOut
     */
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

    /**
     * Convert Session Entity To DTO
     *
     * @param session
     * @return SessionDTOOut
     */
    private SessionDTOOut convertToSessionDto(SessionEntity session) {
        return new SessionDTOOut(session.getId(), session.getTime(), session.getMovieId().getId(), session.getRoomId().getId(), session.getPlaceLeft());
    }

    /**
     * Handle number update of places in session (add: typeOfUpdate == 1|substract: typeOfUpdate == 2)
     *
     * @param sessionId
     * @param typeOfUpdate
     * @return SessionDTOOut
     * @throws BadRequestException
     */
    public SessionDTOOut updatePlaceLeft(Integer sessionId, Integer typeOfUpdate) throws BadRequestException {
        SessionEntity session = this.repository.findById(sessionId).orElseThrow(() -> new NotFoundException("session ", sessionId.toString()));
        if (typeOfUpdate == 1) {
            session.setPlaceLeft(session.getPlaceLeft() + 1);
            session = repository.save(session);
            return convertToSessionDto(session);
        } else if (typeOfUpdate == 2) {
            session.setPlaceLeft(session.getPlaceLeft() - 1);
            session = repository.save(session);
            return convertToSessionDto(session);
        } else {
            throw new BadRequestException();
        }
    }

    /**
     * Delete Session
     *
     * @param id
     * @Throws IllegalArgumentException
     */
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

