package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.ReservationDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.ReservationDTOOut;
import com.cinetoile.SpringAPI.dto.dtoOut.SessionDTOOut;
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

    ReservationService(ReservationRepository repository, SessionRepository sessionRepository, UserRepository userRepository, PricingRepository priceRepository) {
        this.repository = repository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.priceRepository = priceRepository;
    }

    public List<ReservationDTOOut> findAll() {
        List<ReservationEntity> list = repository.findAll();
        return list.stream()
                .map(reservation -> new ReservationDTOOut(reservation.getId(), reservation.getStatus(), reservation.getUserId().getId(), reservation.getSessionId().getId(), reservation.getPriceId().getId()))
                .collect(Collectors.toList());
    }

    public ReservationDTOOut find(Integer id) {
        ReservationEntity reservation = repository.findById(id).orElseThrow(() -> new NotFoundException("reservation ", id.toString()));
        return new ReservationDTOOut(reservation.getId(), reservation.getStatus(), reservation.getUserId().getId(), reservation.getSessionId().getId(), reservation.getPriceId().getId());
    }

    public ReservationDTOOut add(ReservationDTOIn newReservation) {
        SessionEntity session = this.sessionRepository.findById(newReservation.getSessionId()).orElseThrow(() -> new NotFoundException("session ", newReservation.getSessionId().toString()));
        UserEntity user = this.userRepository.findById(newReservation.getUserId()).orElseThrow(() -> new NotFoundException("user ", newReservation.getUserId().toString()));
        PricingEntity price = this.priceRepository.findById(newReservation.getPriceId()).orElseThrow(() -> new NotFoundException("price ", newReservation.getPriceId().toString()));
        ReservationEntity reservation = new ReservationEntity(user, session, price);
        ReservationEntity result = repository.save(reservation);
        return new ReservationDTOOut(result.getId(), result.getStatus(), result.getUserId().getId(), result.getSessionId().getId(), result.getPriceId().getId());
    }

    public ReservationDTOOut updateStatus(Integer id, Integer status) {
        return repository.findById(id).map(reservation -> {
            reservation.setStatus(status);
            reservation = repository.save(reservation);
            return new ReservationDTOOut(reservation.getId(), reservation.getStatus(), reservation.getUserId().getId(), reservation.getSessionId().getId(), reservation.getPriceId().getId());
        }).orElseThrow(() -> new NotFoundException("session ", id.toString()));
    }
}

