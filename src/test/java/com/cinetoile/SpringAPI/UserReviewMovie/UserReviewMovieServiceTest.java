package com.cinetoile.SpringAPI.UserReviewMovie;

import com.cinetoile.SpringAPI.dto.dtoOut.UserReviewMovieDTOOut;
import com.cinetoile.SpringAPI.dto.dtoIn.UserReviewMovieDTOIn;
import com.cinetoile.SpringAPI.models.*;

import com.cinetoile.SpringAPI.repository.UserReviewMovieRepository;
import com.cinetoile.SpringAPI.services.MovieService;
import com.cinetoile.SpringAPI.services.UserReviewMovieService;
import com.cinetoile.SpringAPI.services.UserService;
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
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserReviewMovieServiceTest {

    @Mock
    UserReviewMovieRepository repository;

    @Mock
    UserService userService;

    @Mock
    MovieService movieService;

    @InjectMocks
    UserReviewMovieService service;

    MovieEntity fakeMovie = new MovieEntity("Pulp Fiction", "C'est deux gars qui rentrent dans un coffee shop",
            "120min", "4565325", "Trop bien", 10, null,  new Timestamp(new Date().getTime()), "Quentin Tarantino", "Bruce Willis, John Travolta", 12, "USA");
    MovieEntity fakeMovie2 = new MovieEntity("Forrest Gump", "Un destin incroyable", "120min", "4565394535", "Trop bien", 6, null, new Timestamp(new Date().getTime()), "Robert Zemeckis", "Tom Hanks", 10, "USA");
    UserEntity fakeUser = new UserEntity("Michou", "Michel", "McTest","MichouMcTesty@gmail.com", "testyEnF0rce", 1);

    UserReviewMovieDTOIn newReviewIn = new UserReviewMovieDTOIn(1,3,"Pas ouf, surcôté", "C'est tout", 3);
    UserReviewMovieEntity fakeReview = new UserReviewMovieEntity(fakeMovie, fakeUser, "Pas ouf, surcôté", "C'est tout", 3);
    UserReviewMovieEntity fakeReview2 = new UserReviewMovieEntity(fakeMovie, fakeUser, "Meh", "C'est tout", 4);
    List<UserReviewMovieEntity> fakeUserReviewsList = Arrays.asList(fakeReview, fakeReview2);
    List<UserReviewMovieEntity> fakeMovieReviewsList = Arrays.asList(fakeReview);
    List<UserReviewMovieEntity> fakeUserMovieReviewsList = Arrays.asList(fakeReview2);

    @Before
    public void setUp() {
        Mockito.when(movieService.findById(Mockito.any(Integer.class))).thenReturn(fakeMovie);
        Mockito.when(userService.findById(Mockito.any(Integer.class))).thenReturn(fakeUser);
        Mockito.when(repository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakeReview));
        Mockito.when(repository.save(Mockito.any(UserReviewMovieEntity.class))).thenReturn(fakeReview);
        Mockito.when(repository.findByUserId(Mockito.any(Integer.class))).thenReturn(fakeUserReviewsList);
        Mockito.when(repository.findByMovieId(Mockito.any(Integer.class))).thenReturn(fakeMovieReviewsList);
        Mockito.when(repository.findByUserIdAndMovieId(Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(fakeReview);
        Mockito.when(repository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakeReview));
    }

    @Test
    public void checkGetReview() {
        UserReviewMovieEntity review = service.findById(1);

        Assert.assertEquals("", review.getTitle(), fakeReview.getTitle());
    }

    @Test
    public void checkReviewCreation() {
        UserReviewMovieDTOOut newReview = service.addReview(newReviewIn);

        Assert.assertEquals("", newReview.getRate(), newReviewIn.getRate());
    }

    @Test
    public void checkGetReviewsByUserId() {
        List<UserReviewMovieDTOOut> userReviews = service.findAllByUserId(1);

        Assert.assertEquals("", userReviews.size(), fakeUserReviewsList.size());
    }

    @Test
    public void checkGetReviewsByMovieId() {
        List<UserReviewMovieDTOOut> movieReviews = service.findAllByMovieId(1);

        Assert.assertEquals("", movieReviews.size(), fakeMovieReviewsList.size());
    }

    @Test
    public void checkGetReviewsByUserIdAndMovieId() {
        UserReviewMovieDTOOut userMovieReview = service.findByUserIdMovieId(1, 3);

        Assert.assertEquals("", userMovieReview.getTitle(), fakeReview.getTitle());
    }

    @Test
    public void checkReviewEdition() {
        UserReviewMovieDTOIn updatedReviewReceived = new UserReviewMovieDTOIn(1, 3,"Très bien en fait", "je me suis trompé", 7);
        UserReviewMovieEntity reviewToUpdate = service.findById(1);

        reviewToUpdate.setRate(updatedReviewReceived.getRate());
        reviewToUpdate.setTitle(updatedReviewReceived.getTitle());
        reviewToUpdate.setComment(updatedReviewReceived.getComment());

        repository.save(reviewToUpdate);

        UserReviewMovieDTOOut updatedReview = service.findByUserIdMovieId(3, 1);

        Assert.assertEquals("", updatedReview.getTitle(), updatedReviewReceived.getTitle());
    }
}
