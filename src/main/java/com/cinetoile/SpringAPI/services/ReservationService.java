package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.ReservationDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.ReservationDTOOut;
import com.cinetoile.SpringAPI.dto.dtoOut.ReservationUserListDTOOut;
import com.cinetoile.SpringAPI.models.*;
import com.cinetoile.SpringAPI.repository.PricingRepository;
import com.cinetoile.SpringAPI.repository.ReservationRepository;
import com.cinetoile.SpringAPI.repository.SessionRepository;
import com.cinetoile.SpringAPI.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository repository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final PricingRepository priceRepository;
    private final SessionService sessionService;

    ReservationService(ReservationRepository repository, SessionRepository sessionRepository, UserRepository userRepository, PricingRepository priceRepository, SessionService sessionService) {
        this.repository = repository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.priceRepository = priceRepository;
        this.sessionService = sessionService;
    }

    /**
     * Retrieve one reservation, converted to DTO
     *
     * @param id
     * @return ReservationDTOOut
     */
    public ReservationDTOOut find(Integer id) {
        ReservationEntity reservation = repository.findById(id).orElseThrow(() -> new NotFoundException("reservation ", id.toString()));
        return convertToReservationDto(reservation);
    }

    /**
     * Retrieve all reservation, converted to DTO
     *
     * @return List<ReservationDTOOut>
     */
    public List<ReservationDTOOut> findAll() {
        List<ReservationEntity> list = repository.findAll();
        return list.stream()
                .map(this::convertToReservationDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve all session by user
     *
     * @param id
     * @return List<ReservationUserListDTOOut>
     */
    public List<ReservationUserListDTOOut> findAllByUser(Integer id) {
        return convertToReservationUserListDTOOut(repository.findAllByUserId(userRepository.findById(id).orElseThrow(() -> new NotFoundException("user ", id.toString()))));
    }

    /**
     * Retrieve all reservation within a session
     *
     * @param id
     * @return List<ReservationDTOOut>
     */
    public List<ReservationDTOOut> findAllBySession(Integer id) {
        List<ReservationEntity> list = repository.findAllBySessionIdOrderByStatus(sessionRepository.findById(id).orElseThrow(() -> new NotFoundException("session ", id.toString())));
        return list.stream()
                .map(this::convertToReservationConfirmationDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve all passed reservations for a specific user
     *
     * @param id
     * @return List<ReservationUserListDTOOut>
     */
    public List<ReservationUserListDTOOut> findAllPassedByUser(Integer id) {
        List<ReservationEntity> list = repository.findAllPassedByUser(new Timestamp(new Date().getTime()), userRepository.findById(id).orElseThrow(() -> new NotFoundException("user ", id.toString())).getId());
        return convertToReservationUserListDTOOut(list);
    }

    /**
     * Retrieve all incoming reservations for a specific user
     *
     * @param id
     * @return List<ReservationUserListDTOOut>
     */
    public List<ReservationUserListDTOOut> findAllIncomingByUser(Integer id) {
        List<ReservationEntity> list = repository.findAllIncomingByUser(new Timestamp(new Date().getTime()), id);
        return convertToReservationUserListDTOOut(list);
    }

    /**
     * Add a new Reservation, substract one session place
     *
     * @param newReservation
     * @return ReservationDTOOut
     */
    public ReservationDTOOut add(ReservationDTOIn newReservation) {
        SessionEntity session = sessionRepository.findById(newReservation.getSessionId()).orElseThrow(() -> new NotFoundException("session ", newReservation.getSessionId().toString()));
        UserEntity user = userRepository.findById(newReservation.getUserId()).orElseThrow(() -> new NotFoundException("user ", newReservation.getUserId().toString()));
        PricingEntity price = priceRepository.findById(newReservation.getPriceId()).orElseThrow(() -> new NotFoundException("price ", newReservation.getPriceId().toString()));
        ReservationEntity reservation = new ReservationEntity(user, session, price);
        reservation.setUpdatedAt(new Timestamp(new Date().getTime()));
        reservation.setCreatedAt(new Timestamp(new Date().getTime()));
        ReservationEntity result = repository.save(reservation);
        sessionService.substractSessionPlace(session);
        return convertToReservationDto(result);
    }

    /**
     * Update reservation status, add one session place (recovering place)
     * Status :
     * 0 : pending (user did a reservation)
     * 1 : ok (user did get his ticket)
     * 2 : canceled
     *
     * @param id
     * @param status
     * @return ReservationDTOOut
     */
    public ReservationDTOOut updateStatus(Integer id, Integer status) {
        return repository.findById(id).map(reservation -> {
            reservation.setStatus(status);
            reservation.setUpdatedAt(new Timestamp(new Date().getTime()));
            reservation = repository.save(reservation);
            sessionService.addSessionPlace(reservation.getSessionId());
            return convertToReservationDto(reservation);
        }).orElseThrow(() -> new NotFoundException("session ", id.toString()));
    }

    /**
     * Convert entity to dto
     *
     * @param reservation
     * @return ReservationDTOOut
     */
    private ReservationDTOOut convertToReservationDto(ReservationEntity reservation) {
        return new ReservationDTOOut(reservation.getId(), reservation.getStatus(), reservation.getUserId().getId(), reservation.getSessionId().getId(), reservation.getPriceId().getId());
    }

    /**
     * Convert entity to dto
     *
     * @param reservation
     * @return ReservationDTOOut
     */
    private ReservationDTOOut convertToReservationConfirmationDto(ReservationEntity reservation) {
        return new ReservationDTOOut(reservation.getId(), reservation.getStatus(), reservation.getUserId().getId(), reservation.getSessionId().getId(), reservation.getPriceId().getId(), reservation.getUserId().getFirstname(), reservation.getUserId().getLastname());
    }

    /**
     * Convert List of entity to list of dto
     *
     * @param list
     * @return List<ReservationUserListDTOOut>
     */
    private List<ReservationUserListDTOOut> convertToReservationUserListDTOOut(List<ReservationEntity> list) {
        return list.stream()
                .map(reservation -> new ReservationUserListDTOOut(reservation.getId(),
                        reservation.getStatus(),
                        reservation.getPriceId().getPrice(),
                        reservation.getPriceId().getName(),
                        reservation.getSessionId().getRoomId().getTheater().getName(),
                        reservation.getSessionId().getMovieId().getName(),
                        reservation.getSessionId().getTime()))
                .collect(Collectors.toList());
    }
}

