package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.SessionDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.SessionDTOOut;
import com.cinetoile.SpringAPI.dto.dtoOut.SessionTheaterDTOOut;
import com.cinetoile.SpringAPI.exceptions.BadRequestException;
import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.models.RoomEntity;
import com.cinetoile.SpringAPI.models.SessionEntity;
import com.cinetoile.SpringAPI.repository.MovieRepository;
import com.cinetoile.SpringAPI.repository.RoomRepository;
import com.cinetoile.SpringAPI.repository.SessionRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {

    private final SessionRepository repository;
    private final RoomRepository roomRepository;
    private final MovieRepository movieRepository;
    private final TheaterService theaterService;

    SessionService(SessionRepository repository, RoomRepository roomRepository, MovieRepository movieRepository, TheaterService theaterService) {
        this.repository = repository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.theaterService = theaterService;
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
     * Find Session
     *
     * @param id
     * @return SessionDTOOut
     */
    public SessionDTOOut findDto(Integer id) {
        return convertToSessionDto(find(id));
    }

    /**
     * Find all sessions
     *
     * @return SessionDTOOut
     */
    public List<SessionDTOOut> findAll() {
        List<SessionEntity> list = repository.findAll();
        return convertToSessionListDto(list);
    }

    /**
     * Find sessions by movie
     *
     * @param id
     * @return SessionDTOOut
     */
    public List<SessionDTOOut> findByMovie(Integer id) {
        MovieEntity movie = movieRepository.findById(id).orElseThrow(() -> new NotFoundException("movie ", id.toString()));
        List<SessionEntity> list = repository.findByMovieId(movie);
        return convertToSessionListDto(list);
    }

    /**
     * Find sessions between 2 dates (7 days queried)
     *
     * @param id
     * @return SessionDTOOut
     */
    public List<SessionDTOOut> findByMovieFromDate(Integer id, Date fromDate) {
        MovieEntity movie = movieRepository.findById(id).orElseThrow(() -> new NotFoundException("movie ", id.toString()));
        Date toDate = fromDate;
        Calendar c = Calendar.getInstance();
        c.setTime(toDate);
        c.add(Calendar.DATE, 7);
        toDate = c.getTime();
        List<SessionEntity> list = repository.findByMovieIdAndTimeBetween(movie, fromDate, toDate);
        return convertToSessionListDto(list);
    }

    /**
     * Find sessions by theater for a specific day
     *
     * @param id
     * @param date
     * @return List<SessionDTOOut>
     */
    @SneakyThrows
    public List<SessionTheaterDTOOut> findByTheater(Integer id, Date date) {
        theaterService.findById(id);
        List<SessionEntity> sessions = repository.findByTheaterId(id, atStartOfDay(date), atEndOfDay(date));
        return sessions.stream()
                .map(this::convertToSessionTheaterDto)
                .collect(Collectors.toList());
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
        session.setUpdatedAt(new Timestamp(new Date().getTime()));
        session.setCreatedAt(new Timestamp(new Date().getTime()));
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
            session.setUpdatedAt(new Timestamp(new Date().getTime()));
            session = repository.save(session);
            return convertToSessionDto(session);
        }).orElseThrow(() -> new NotFoundException("session ", id.toString()));
    }

    /**
     * Delete session
     *
     * @param id
     * @return
     */
    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
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
            return convertToSessionDto(substractSessionPlace(session));
        } else if (typeOfUpdate == 2) {
            return convertToSessionDto(addSessionPlace(session));
        } else {
            throw new BadRequestException();
        }
    }

    /**
     * Add one place in a session session
     *
     * @param session
     * @return SessionEntity
     */
    public SessionEntity addSessionPlace(SessionEntity session) {
        session.setPlaceLeft(session.getPlaceLeft() + 1);
        session.setUpdatedAt(new Timestamp(new Date().getTime()));
        return repository.save(session);
    }

    /**
     * Remove one place in a session
     *
     * @param session
     * @return SessionEntity
     */
    public SessionEntity substractSessionPlace(SessionEntity session) {
        session.setPlaceLeft(session.getPlaceLeft() - 1);
        session.setUpdatedAt(new Timestamp(new Date().getTime()));
        return repository.save(session);
    }

    /**
     * Convert session entity to SessionDtoOut
     *
     * @param session
     * @return SessionDTOOut
     */
    private SessionDTOOut convertToSessionDto(SessionEntity session) {
        return new SessionDTOOut(session.getId(), session.getTime(), session.getMovieId().getId(), session.getRoomId().getId(), session.getPlaceLeft());
    }

    /**
     * Convert session entity to SessionTheaterDtoOut
     *
     * @param session
     * @return SessionTheaterDtoOut
     */
    private SessionTheaterDTOOut convertToSessionTheaterDto(SessionEntity session) {
        return new SessionTheaterDTOOut(session.getId(), session.getTime(), session.getMovieId().getName(), session.getRoomId().getName(), session.getRoomId().getPlace() - session.getPlaceLeft());
    }

    private List<SessionDTOOut> convertToSessionListDto(List<SessionEntity> sessions) {
        return sessions.stream()
                .map(this::convertToSessionDto)
                .collect(Collectors.toList());
    }

    /**
     * Returns the date at the start of day of the given date
     *
     * @param date
     * @return Date
     */
    private Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    /**
     * Returns the date at the end of day of the given date
     *
     * @param date
     * @return Date
     */
    private Date atEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    /**
     * Convert date to localDateTime
     *
     * @param date
     * @return LocalDateTime
     */
    private LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Convert LocalDateTime to date
     *
     * @param localDateTime
     * @return Date
     */
    private Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}

