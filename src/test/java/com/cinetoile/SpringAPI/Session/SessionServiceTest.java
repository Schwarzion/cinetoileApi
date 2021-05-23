package com.cinetoile.SpringAPI.Session;

import com.cinetoile.SpringAPI.dto.dtoIn.SessionDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.SessionDTOOut;
import com.cinetoile.SpringAPI.models.*;
import com.cinetoile.SpringAPI.repository.*;
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

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SessionServiceTest {
    @Mock
    SessionRepository sessionRepository;

    @Mock
    MovieRepository movieRepository;

    @Mock
    RoomRepository roomRepository;

    @InjectMocks
    SessionService sessionService;

    TheaterEntity fakeTheater = new TheaterEntity("Ciné'croyable", "Incroyable", "Rue bidon", "123", "Lille", "59000", "0606060606", "cinema@incroyable.com", null);
    RoomEntity fakeRoom = new RoomEntity("Salle 1", 50, fakeTheater);
    MovieEntity fakeMovie = new MovieEntity("Pulp Fiction", "C'est deux gars qui rentrent dans un coffee shop", "120min", "4565325", "Trop bien", 10, null, new Timestamp(new Date().getTime()), "Quentin Tarantino", "Bruce Willis, John Travolta", 12, "USA");
    SessionEntity fakeSession = new SessionEntity(fakeRoom, fakeMovie, new Timestamp(new Date().getTime()));
    List<SessionEntity> fakeSessionList = Arrays.asList(fakeSession, fakeSession);
    SessionDTOIn fakeSessionDTOIn = new SessionDTOIn(new Timestamp(new Date().getTime()), 1, 1);

    @Before
    public void setUp() {
        Mockito.when(sessionRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakeSession));
        Mockito.when(sessionRepository.findByMovieIdAndTimeBetween(Mockito.any(MovieEntity.class), Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(fakeSessionList);
        Mockito.when(sessionRepository.findByMovieId(Mockito.any(MovieEntity.class))).thenReturn(fakeSessionList);
        Mockito.when(sessionRepository.findAll()).thenReturn(fakeSessionList);
        Mockito.when(movieRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakeMovie));
        Mockito.when(roomRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakeRoom));
        Mockito.when(sessionRepository.save(Mockito.any(SessionEntity.class))).thenReturn(fakeSession);
    }

    @Test
    public void findDto() {
        SessionDTOOut session = sessionService.findDto(1);
        Assert.assertTrue(session instanceof SessionDTOOut);
        Assert.assertEquals(session.getMovieId(), fakeSession.getMovieId().getId());
        Assert.assertEquals(session.getRoomId(), fakeSession.getRoomId().getId());
    }

    @Test
    public void find() {
        SessionEntity session = sessionService.find(1);
        Assert.assertTrue(session instanceof SessionEntity);
        Assert.assertEquals(session.getMovieId().getId(), fakeSession.getMovieId().getId());
        Assert.assertEquals(session.getRoomId().getId(), fakeSession.getRoomId().getId());
    }

    @Test
    public void add() {
        SessionDTOOut session = sessionService.add(fakeSessionDTOIn);
        Assert.assertTrue(session instanceof SessionDTOOut);
        Assert.assertEquals(session.getMovieId(), fakeSession.getMovieId().getId());
        Assert.assertEquals(session.getRoomId(), fakeSession.getRoomId().getId());
    }

    @Test
    public void update() {
        SessionDTOOut session = sessionService.update(fakeSessionDTOIn, 1);
        Assert.assertTrue(session instanceof SessionDTOOut);
        Assert.assertEquals(session.getMovieId(), fakeSession.getMovieId().getId());
        Assert.assertEquals(session.getRoomId(), fakeSession.getRoomId().getId());
    }

    @Test
    public void findAll() {
        List<SessionDTOOut> sessions = sessionService.findAll();
        Assert.assertEquals("", sessions.size(), fakeSessionList.size());
    }

    @Test
    public void findByMovie() {
        List<SessionDTOOut> sessions = sessionService.findByMovie(1);
        Assert.assertEquals("", sessions.size(), fakeSessionList.size());
    }

    @Test
    public void findByMovieFromDAte() {
        List<SessionDTOOut> sessions = sessionService.findByMovieFromDAte(1, new Date());
        Assert.assertEquals("", sessions.size(), fakeSessionList.size());
    }

    @Test
    public void addPlace() {
        SessionDTOOut session = sessionService.updatePlaceLeft(1, 2);
        Assert.assertTrue(session instanceof SessionDTOOut);
        Assert.assertEquals(session.getMovieId(), fakeSession.getMovieId().getId());
        Assert.assertEquals(session.getRoomId(), fakeSession.getRoomId().getId());
    }

    @Test
    public void subPlace() {
        SessionDTOOut session = sessionService.updatePlaceLeft(1, 1);
        Assert.assertTrue(session instanceof SessionDTOOut);
        Assert.assertEquals(session.getMovieId(), fakeSession.getMovieId().getId());
        Assert.assertEquals(session.getRoomId(), fakeSession.getRoomId().getId());
    }

}
