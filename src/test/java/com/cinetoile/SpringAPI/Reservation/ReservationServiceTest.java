package com.cinetoile.SpringAPI.Reservation;

import com.cinetoile.SpringAPI.dto.dtoIn.ReservationDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.ReservationDTOOut;
import com.cinetoile.SpringAPI.dto.dtoOut.ReservationUserListDTOOut;
import com.cinetoile.SpringAPI.models.*;
import com.cinetoile.SpringAPI.repository.PricingRepository;
import com.cinetoile.SpringAPI.repository.ReservationRepository;
import com.cinetoile.SpringAPI.repository.SessionRepository;
import com.cinetoile.SpringAPI.repository.UserRepository;
import com.cinetoile.SpringAPI.services.ReservationService;
import com.cinetoile.SpringAPI.services.SessionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {
    @Mock
    PricingRepository pricingRepository;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    SessionRepository sessionRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    SessionService sessionService;

    @InjectMocks
    ReservationService reservationService;

    TheaterEntity fakeTheater = new TheaterEntity("Ciné'croyable", "Incroyable", "Rue bidon", "123", "Lille", "59000", "0606060606", "cinema@incroyable.com", null);
    RoomEntity fakeRoom = new RoomEntity("Salle 1", 50, fakeTheater);
    PricingEntity fakePricing = new PricingEntity("testPrice", new BigDecimal("42.000"));
    MovieEntity fakeMovie = new MovieEntity("Pulp Fiction", "C'est deux gars qui rentrent dans un coffee shop", "120min", "4565325", "Trop bien", 10, null, new Timestamp(new Date().getTime()), "Quentin Tarantino", "Bruce Willis, John Travolta", 12, "USA");
    SessionEntity fakeSession = new SessionEntity(fakeRoom, fakeMovie, new Timestamp(new Date().getTime()));
    UserEntity fakeUser = new UserEntity("Michou", "Michel", "McTest","MichouMcTesty@gmail.com", "testyEnF0rce", 1);
    UserEntity fakeUser2 = new UserEntity("Michou", "Michel", "McTest","MichouMcTesty@gmail.com", "testyEnF0rce", 1);


    ReservationEntity fakeReservation = new ReservationEntity(fakeUser, fakeSession, fakePricing);
    ReservationEntity fakeReservation2 = new ReservationEntity(fakeUser2, fakeSession, fakePricing);
    List<ReservationEntity> fakeReservationList = Arrays.asList(fakeReservation, fakeReservation, fakeReservation2);

    ReservationDTOOut fakeReservationDtoOut = new ReservationDTOOut(1, 0, 1, 1, 1);
    ReservationDTOIn fakeReservationDtoIn = new ReservationDTOIn(1, 1, 1);

    @Before
    public void setUp() {
        Mockito.when(reservationRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakeReservation));
        Mockito.when(reservationRepository.findAll()).thenReturn(fakeReservationList);
        Mockito.when(reservationRepository.findAllByUserId(Mockito.any(UserEntity.class))).thenReturn(fakeReservationList);
        Mockito.when(reservationRepository.findAllBySessionIdOrderByStatus(Mockito.any(SessionEntity.class))).thenReturn(fakeReservationList);
        Mockito.when(reservationRepository.findAllIncomingByUser(Mockito.any(Date.class), Mockito.any(Integer.class))).thenReturn(fakeReservationList);
        Mockito.when(reservationRepository.save(Mockito.any(ReservationEntity.class))).thenReturn(fakeReservation);
        Mockito.when(userRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of((fakeUser)));
        Mockito.when(pricingRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakePricing));
        Mockito.when(sessionRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakeSession));
        Mockito.when(sessionRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakeSession));
        Mockito.when(sessionService.substractSessionPlace(Mockito.any(SessionEntity.class))).thenReturn(fakeSession);
        Mockito.when(sessionService.addSessionPlace(Mockito.any(SessionEntity.class))).thenReturn(fakeSession);
    }

    @Test
    public void find() {
        ReservationDTOOut reservation = reservationService.find(1);
        Assert.assertEquals(reservation.getPriceId(), fakeReservation.getPriceId().getId());
        Assert.assertEquals(reservation.getSessionId(), fakeReservation.getSessionId().getId());
    }

    @Test
    public void findAll() {
        List<ReservationDTOOut> reservations = reservationService.findAll();
        Assert.assertEquals("", reservations.size(), fakeReservationList.size());
    }

    @Test
    public void findAllByUser() {
        List<ReservationUserListDTOOut> reservations = reservationService.findAllByUser(1);
        Assert.assertEquals("", reservations.size(), fakeReservationList.size());
    }

    @Test
    public void findAllBySession() {
        List<ReservationDTOOut> reservations = reservationService.findAllBySession(1);
        Assert.assertEquals("", reservations.size(), fakeReservationList.size());
    }

    @Test
    public void findAllIncomingByUser() {
        List<ReservationUserListDTOOut> reservations = reservationService.findAllIncomingByUser(1);
        Assert.assertEquals("", reservations.size(), fakeReservationList.size());
    }

    @Test
    public void add() {
        ReservationDTOOut reservation = reservationService.add(fakeReservationDtoIn);
        Assert.assertEquals(reservation.getStatus(), fakeReservationDtoOut.getStatus());
    }

    @Test
    public void updateStatus() {
        fakeReservation.setStatus(1);
        ReservationDTOOut reservation = reservationService.updateStatus(1, 1);
        Assert.assertEquals(reservation.getStatus(), fakeReservation.getStatus());
    }
}
