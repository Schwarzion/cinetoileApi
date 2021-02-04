package com.cinetoile.SpringAPI.Movie;

import com.cinetoile.SpringAPI.dto.In.MovieDTOIn;
import com.cinetoile.SpringAPI.dto.Out.MovieDTOOut;
import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.repository.MovieRepository;
import com.cinetoile.SpringAPI.services.MovieService;
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
public class MovieServiceTest {

    @Mock
    MovieRepository repository;

    @InjectMocks
    MovieService service;

    MovieEntity fakeMovie = new MovieEntity("Pulp Fiction", "C'est deux gars qui rentrent dans un coffee shop", "120min", "4565325", "Trop bien", 10, null, new Timestamp(new Date().getTime()), "Quentin Tarantino", "Bruce Willis, John Travolta", 12, "USA");
    MovieEntity fakeMovie2 = new MovieEntity("Forrest Gump", "Un destin incroyable", "120min", "4565394535", "Trop bien", 6, null, new Timestamp(new Date().getTime()), "Robert Zemeckis", "Tom Hanks", 10, "USA");

    MovieDTOIn newMovieIn = new MovieDTOIn("Pulp Fiction", "C'est deux gars qui rentrent dans un coffee shop", "120min", "4565325", "Trop bien", 10, null,  new Timestamp(new Date().getTime()), "Quentin Tarantino", "Bruce Willis, John Travolta", 12, "USA");
    MovieDTOOut newMovieOut = new MovieDTOOut(1, "Pulp Fiction", "C'est deux gars qui rentrent dans un coffee shop", "120min", "4565325", "Trop bien", 10, null, new Timestamp(new Date().getTime()), "Quentin Tarantino", "Bruce Willis, John Travolta", 12, "USA");
    List<MovieEntity> fakeMoviesList = Arrays.asList(fakeMovie, fakeMovie2);

    @Before
    public void setUp() {
        Mockito.when(repository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakeMovie));
        Mockito.when(repository.save(Mockito.any(MovieEntity.class))).thenReturn(fakeMovie);
    }

    @Test
    public void checkGetMovie() {
        MovieEntity movie = service.findById(1);
        Assert.assertEquals("", movie.getName(), fakeMovie.getName());
    }

    @Test
    public void checkAddMovie() {
        MovieDTOOut newMovie = service.add(newMovieIn);
        Assert.assertEquals("", newMovie.getName(), newMovieIn.getName());

    }
}
