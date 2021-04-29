package com.cinetoile.SpringAPI.TheaterMovie;

import com.cinetoile.SpringAPI.dto.dtoOut.TheaterMovieDTOOut;
import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.models.TheaterEntity;
import com.cinetoile.SpringAPI.models.TheaterMovieEntity;
import com.cinetoile.SpringAPI.repository.TheaterMovieRepository;
import com.cinetoile.SpringAPI.services.MovieService;
import com.cinetoile.SpringAPI.services.TheaterMovieService;
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
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TheaterMovieServiceTest {

    @Mock
    TheaterMovieRepository repository;

    @Mock
    MovieService movieService;

    @Mock
    TheaterService theaterService;

    @InjectMocks
    TheaterMovieService service;

    MovieEntity fakeMovie = new MovieEntity("Pulp Fiction", "C'est deux gars qui rentrent dans un coffee shop", "120min", "4565325", "Trop bien", 10, null,  new Timestamp(new Date().getTime()), "Quentin Tarantino", "Bruce Willis, John Travolta", 12, "USA");
    MovieEntity fakeMovie2 = new MovieEntity("Forrest Gump", "Un destin incroyable", "120min", "4565394535", "Trop bien", 6, null, new Timestamp(new Date().getTime()), "Robert Zemeckis", "Tom Hanks", 10, "USA");
    TheaterEntity fakeTheater = new TheaterEntity("Le magistral", "Petit ciné sympa", "RUE DES PAPILLONS", "16", "LILLE", "59000", "0617998352", "magistral@mail.com", null);
    TheaterEntity fakeTheater2 = new TheaterEntity("Le grand écran", "Gros ciné sympa", "RUE DES FAISANS", "98", "LILLE", "59000", "0617998656", "bigscreen@mail.com", null);
    TheaterMovieEntity fakeTheaterMovie = new TheaterMovieEntity(new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), true, false, fakeMovie, fakeTheater);
    TheaterMovieEntity fakeTheaterMovie2 = new TheaterMovieEntity();
    List<TheaterMovieEntity> fakeTheaterMovieList = Arrays.asList(fakeTheaterMovie, fakeTheaterMovie2);

    @Before
    public void setUp() {
        Mockito.when(repository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakeTheaterMovie));
    }

    @Test
    public void checkGetOneTheaterMovie() {
        TheaterMovieDTOOut theaterMovie = service.findOne(1);
        Assert.assertEquals("", theaterMovie.getId(), fakeTheaterMovie.getId());
    }
}
