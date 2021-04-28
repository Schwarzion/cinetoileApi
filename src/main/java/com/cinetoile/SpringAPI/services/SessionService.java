package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.SessionDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.SessionDTOOut;
import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.models.RoomEntity;
import com.cinetoile.SpringAPI.models.SessionEntity;
import com.cinetoile.SpringAPI.repository.MovieRepository;
import com.cinetoile.SpringAPI.repository.RoomRepository;
import com.cinetoile.SpringAPI.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
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
        return new SessionDTOOut(session.getTime(), session.getMovieId().getId(), session.getRoomId().getId());
    }
    public SessionEntity add(SessionEntity newSession) {
        return repository.save(newSession);
    }

    public SessionEntity update(SessionDTOIn newSession, Integer id) {
            MovieEntity movie = this.movieRepository.findById(newSession.getMovieId()).orElseThrow(() -> new NotFoundException("movie ", newSession.getMovieId().toString()));
            RoomEntity room = this.roomRepository.findById(newSession.getRoomId()).orElseThrow(() -> new NotFoundException("room ", newSession.getRoomId().toString()));
        return repository.findById(id).map(session -> {
            session.setTime(newSession.getTime());
            session.setMovieId(movie);
            session.setRoomId(room);
            session.setCreatedAt(new Timestamp(new Date().getTime()));
            session.setUpdatedAt(new Timestamp(new Date().getTime()));
            return repository.save(session);
        }).orElseThrow(() -> new NotFoundException("session ", id.toString()));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

