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
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository repository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final PricingRepository priceRepository;

    ReservationService(ReservationRepository repository, SessionRepository sessionRepository, UserRepository userRepository, PricingRepository priceRepository) {
        this.repository = repository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.priceRepository = priceRepository;
    }

    public ReservationDTOOut find(Integer id) {
        ReservationEntity reservation = repository.findById(id).orElseThrow(() -> new NotFoundException("reservation ", id.toString()));
        return convertToReservationDto(reservation);
    }

    public List<ReservationDTOOut> findAll() {
        List<ReservationEntity> list = repository.findAll();
        return list.stream()
                .map(this::convertToReservationDto)
                .collect(Collectors.toList());
    }

    public List<ReservationUserListDTOOut> findAllByUser(Integer id) {
        return convertToReservationUserListDTOOut(repository.findAllByUserId(userRepository.findById(id).orElseThrow(() -> new NotFoundException("user ", id.toString()))));
    }

    public List<ReservationDTOOut> findAllBySession(Integer id) {
        List<ReservationEntity> list = repository.findAllBySessionIdOrderByStatus(sessionRepository.findById(id).orElseThrow(() -> new NotFoundException("session ", id.toString())));
        return list.stream()
                .map(this::convertToReservationDto)
                .collect(Collectors.toList());
    }

    public List<ReservationUserListDTOOut> findAllPassedByUser(Integer id) {
        List<ReservationEntity> list = repository.findAllPassedByUser(new Timestamp(new Date().getTime()), userRepository.findById(id).orElseThrow(() -> new NotFoundException("user ", id.toString())).getId());
        return convertToReservationUserListDTOOut(list);
    }

    public List<ReservationUserListDTOOut> findAllIncomingByUser(Integer id) {
        List<ReservationEntity> list = repository.findAllIncomingByUser(new Timestamp(new Date().getTime()), id);
        return convertToReservationUserListDTOOut(list);
    }

    public ReservationDTOOut add(ReservationDTOIn newReservation) {
        SessionEntity session = this.sessionRepository.findById(newReservation.getSessionId()).orElseThrow(() -> new NotFoundException("session ", newReservation.getSessionId().toString()));
        UserEntity user = this.userRepository.findById(newReservation.getUserId()).orElseThrow(() -> new NotFoundException("user ", newReservation.getUserId().toString()));
        PricingEntity price = this.priceRepository.findById(newReservation.getPriceId()).orElseThrow(() -> new NotFoundException("price ", newReservation.getPriceId().toString()));
        ReservationEntity reservation = new ReservationEntity(user, session, price);
        ReservationEntity result = repository.save(reservation);
        return convertToReservationDto(result);
    }

    public ReservationDTOOut updateStatus(Integer id, Integer status) {
        return repository.findById(id).map(reservation -> {
            reservation.setStatus(status);
            reservation = repository.save(reservation);
            return convertToReservationDto(reservation);
        }).orElseThrow(() -> new NotFoundException("session ", id.toString()));
    }

    private ReservationDTOOut convertToReservationDto(ReservationEntity reservation) {
        return new ReservationDTOOut(reservation.getId(), reservation.getStatus(), reservation.getUserId().getId(), reservation.getSessionId().getId(), reservation.getPriceId().getId());
    }

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

