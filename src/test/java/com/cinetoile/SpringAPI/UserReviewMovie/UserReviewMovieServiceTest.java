package com.cinetoile.SpringAPI.UserReviewMovie;

import com.cinetoile.SpringAPI.Dto.In.UserReviewMovieDTOIn;
import com.cinetoile.SpringAPI.Dto.Out.UserReviewMovieDTOOut;
import com.cinetoile.SpringAPI.models.Movie;
import com.cinetoile.SpringAPI.models.User;
import com.cinetoile.SpringAPI.models.UserReviewMovie;
import com.cinetoile.SpringAPI.models.UserReviewMoviePK;
import com.cinetoile.SpringAPI.repository.UserReviewMovieRepository;
import com.cinetoile.SpringAPI.services.MovieService;
import com.cinetoile.SpringAPI.services.UserReviewMovieService;
import com.cinetoile.SpringAPI.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.sql.Array;
import java.util.Arrays;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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

    Movie fakeMovie = new Movie("Pulp Fiction", "C'est deux gars qui rentrent dans un coffee shop", "120min", "4565325", "Trop bien", 10, new Timestamp(new Date().getTime()), "Quentin Tarantino", "Bruce Willis, John Travolta", 12, "USA");
    Movie fakeMovie2 = new Movie("Forrest Gump", "Un destin incroyable", "120min", "4565394535", "Trop bien", 6, new Timestamp(new Date().getTime()), "Robert Zemeckis", "Tom Hanks", 10, "USA");
    User fakeUser = new User("Michel", "McTest", "Lille", "59000", new Timestamp(new Date().getTime()), 1, "0606060606", "MichouMcTesty@gmail.com", "testyEnF0rce" );

    UserReviewMovieDTOOut newReviewOut = new UserReviewMovieDTOOut("Pulp Fiction", "test", 10);
    UserReviewMovieDTOIn newReviewIn = new UserReviewMovieDTOIn(fakeMovie.getId(),fakeUser.getId(),"Pas ouf, surcôté", "C'est tout", 3);
    UserReviewMovie fakeReview = new UserReviewMovie(new UserReviewMoviePK(fakeMovie, fakeUser), "Pas ouf, surcôté", "C'est tout", 3);
    UserReviewMovie fakeReview2 = new UserReviewMovie(new UserReviewMoviePK(fakeMovie2, fakeUser), "Meh", "C'est tout", 4);
    List<UserReviewMovie> fakeUserReviewsList = Arrays.asList(fakeReview, fakeReview2);
    List<UserReviewMovie> fakeMovieReviewsList = Arrays.asList(fakeReview);
    //List<UserReviewMovie> fakeUserMovieReviewsList = Arrays.asList(fakeReview2);

    @Before
    public void setUp() {
        Mockito.when(movieService.findById(Mockito.any(Integer.class))).thenReturn(fakeMovie);
        Mockito.when(userService.findById(Mockito.any(Integer.class))).thenReturn(fakeUser);
        Mockito.when(repository.findById(Mockito.any(UserReviewMoviePK.class))).thenReturn(Optional.of(fakeReview));
        Mockito.when(repository.save(Mockito.any(UserReviewMovie.class))).thenReturn(fakeReview);
        Mockito.when(repository.findByIdUserId(Mockito.any(Integer.class))).thenReturn(fakeUserReviewsList);
        Mockito.when(repository.findByIdMovieId(Mockito.any(Integer.class))).thenReturn(fakeMovieReviewsList);
        Mockito.when(repository.findByIdUserIdAndIdMovieId(Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(fakeReview);
    }

    @Test
    public void checkGetReview() {
        UserReviewMovie review = service.findById(fakeReview.getId());

        Assert.assertEquals("", review.getTitle(), fakeReview.getTitle());
    }

    @Test
    public void checkReviewCreation() {
        UserReviewMovieDTOOut newReview = service.addReview(newReviewIn);

        Assert.assertEquals("", newReview.getRate(), newReviewIn.getRate());
    }

    @Test
    public void checkGetReviewsByUserId() {
        List<UserReviewMovie> userReviews = repository.findByIdUserId(1);

        Assert.assertEquals("", userReviews.size(), fakeUserReviewsList.size());
    }

    @Test
    public void checkGetReviewsByMovieId() {
        List<UserReviewMovie> movieReviews = repository.findByIdMovieId(1);

        Assert.assertEquals("", movieReviews.size(), fakeMovieReviewsList.size());
    }

    @Test
    public void checkGetReviewsByUserIdAndMovieId() {
        UserReviewMovie userMovieReview = repository.findByIdUserIdAndIdMovieId(1, 3);

        Assert.assertEquals("", userMovieReview.getTitle(), fakeReview.getTitle());
    }

    @Test
    public void checkReviewEdition() {
        UserReviewMovie updatedReviewReceived = new UserReviewMovie(new UserReviewMoviePK(fakeMovie, fakeUser), "Très bien en fait", "je me suis trompé", 7);
        UserReviewMovie reviewToUpdate = repository.findByIdUserIdAndIdMovieId(1, 3);

        reviewToUpdate.setRate(updatedReviewReceived.getRate());
        reviewToUpdate.setTitle(updatedReviewReceived.getTitle());
        reviewToUpdate.setComment(updatedReviewReceived.getComment());

        repository.save(reviewToUpdate);

        UserReviewMovie updatedReview = repository.findByIdUserIdAndIdMovieId(1, 3);

        Assert.assertEquals("", updatedReview.getTitle(), updatedReviewReceived.getTitle());
    }
}
