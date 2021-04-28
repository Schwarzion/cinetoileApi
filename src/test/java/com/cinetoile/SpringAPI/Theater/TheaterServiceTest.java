package com.cinetoile.SpringAPI.Theater;

import com.cinetoile.SpringAPI.dto.dtoIn.TheaterDTOIn;
import com.cinetoile.SpringAPI.models.TheaterEntity;
import com.cinetoile.SpringAPI.repository.TheaterRepository;
import com.cinetoile.SpringAPI.services.TheaterService;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TheaterServiceTest {

    @Mock
    TheaterRepository repository;

    @InjectMocks
    TheaterService service;

    TheaterEntity fakeTheater = new TheaterEntity("Ciné'croyable", "Incroyable", "Rue bidon", "123", "Lille", "59000", "0606060606", "cinema@incroyable.com", null);
    List<TheaterEntity> fakeTheaterList = Arrays.asList(fakeTheater);
    TheaterDTOIn fakeTheaterIn = new TheaterDTOIn("Ciné'croyable", "Incroyable", "Rue bidon", "123", "Lille", "59000", "0606060606", "cinema@incroyable.com", null);

    @Before
    public void setUp() {
        Mockito.when(repository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakeTheater));
        Mockito.when(repository.findAll()).thenReturn(fakeTheaterList);
        Mockito.when(repository.save(Mockito.any(TheaterEntity.class))).thenReturn(fakeTheater);
    }

    @Test
    public void testGetTheaterById() {
        TheaterEntity theater = service.findById(1);

        Assert.assertEquals("", theater.getName(), fakeTheater.getName());
    }

    @Test
    public void testTheaterCreation() {
        TheaterEntity newTheater = service.add(fakeTheaterIn);

        Assert.assertEquals("", fakeTheaterIn.getName(), newTheater.getName());
    }

    @Test
    public void testGetAllTheaters() {
        List<TheaterEntity> theaterList = service.findAll();
        Assert.assertEquals("", theaterList.size(), fakeTheaterList.size());
    }

    @Test
    public void testTheaterEdition() {
        TheaterDTOIn updatedTheaterReceived = new TheaterDTOIn("Ciné'croyable", "Incroyable", "Rue legit", "123", "Lille", "59000", "0606060606", "cinema@incroyable.com", null);

        TheaterEntity updatedTheater = service.update(updatedTheaterReceived, 1);

        Assert.assertEquals("", updatedTheater.getAddress(), updatedTheaterReceived.getAddress());
    }
}
