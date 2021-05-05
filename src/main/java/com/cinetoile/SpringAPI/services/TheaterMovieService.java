package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.TheaterMovieDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.ReservationDTOOut;
import com.cinetoile.SpringAPI.dto.dtoOut.ReservationUserListDTOOut;
import com.cinetoile.SpringAPI.dto.dtoOut.TheaterMovieDTOOut;
import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.models.ReservationEntity;
import com.cinetoile.SpringAPI.models.TheaterEntity;
import com.cinetoile.SpringAPI.models.TheaterMovieEntity;
import com.cinetoile.SpringAPI.repository.TheaterMovieRepository;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TheaterMovieService {
    private final TheaterMovieRepository repository;
    private final TheaterService theaterService;
    private final MovieService movieService;

    //Constructor
    TheaterMovieService(TheaterMovieRepository repository, TheaterService theaterService, MovieService movieService) {
        this.repository = repository;
        this.movieService = movieService;
        this.theaterService = theaterService;
    }

    //ConvertToDTO methods
    private TheaterMovieDTOOut convertToTheaterMovieDto(TheaterMovieEntity theaterMovie) throws IOException {
        return new TheaterMovieDTOOut(
                theaterMovie.getId(),
                theaterMovie.getTheater().getId(),
                theaterMovie.getStartDate(),
                theaterMovie.getEndDate(),
                theaterMovie.getStatus(),
                theaterMovie.getTheaterFav(),
                theaterMovie.getMovie().getId(),
                getFileFromPath(theaterMovie.getMovie().getImage()),
                theaterMovie.getMovie().getName(),
                theaterMovie.getMovie().getDuration()
        );
    }

    private List<TheaterMovieDTOOut> convertToListTheaterMovieDto(List<TheaterMovieEntity> list) {
        return list.stream()
                .map(theaterMovie -> {
                    try {
                        return new TheaterMovieDTOOut(
                                theaterMovie.getId(),
                                theaterMovie.getTheater().getId(),
                                theaterMovie.getStartDate(),
                                theaterMovie.getEndDate(),
                                theaterMovie.getStatus(),
                                theaterMovie.getTheaterFav(),
                                theaterMovie.getMovie().getId(),
                                getFileFromPath(theaterMovie.getMovie().getImage()),
                                theaterMovie.getMovie().getName(),
                                theaterMovie.getMovie().getDuration());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    //Get methods
    public List<TheaterMovieDTOOut> findAll() {
        List<TheaterMovieEntity> list = repository.findAll();
        return convertToListTheaterMovieDto(list);
    }

    public List<TheaterMovieDTOOut> findAllEnabled() {
        List<TheaterMovieEntity> list = repository.findAllEnabled();
        return convertToListTheaterMovieDto(list);
    }

    public TheaterMovieDTOOut findOne(Integer id) throws IOException {
        TheaterMovieEntity theaterMovie = repository.findById(id).orElseThrow(() -> new NotFoundException("theaterMovie", id.toString()));
        return convertToTheaterMovieDto(theaterMovie);
    }

    public List<TheaterMovieDTOOut> findByTheaterId(Integer theaterId) {
        List<TheaterMovieEntity> list = repository.findByTheaterId(theaterId);
        return convertToListTheaterMovieDto(list);
    }

    public List<TheaterMovieDTOOut> findEnabledByTheaterId(Integer theaterId) {
        List<TheaterMovieEntity> list = repository.findEnabledByTheaterId(theaterId);
        return convertToListTheaterMovieDto(list);
    }

    public List<TheaterMovieDTOOut> findByMovieId(Integer movieId) {
        List<TheaterMovieEntity> list = repository.findByMovieId(movieId);
        return convertToListTheaterMovieDto(list);
    }

    public TheaterMovieDTOOut findByTheaterIdMovieId(Integer theaterId, Integer movieId) throws IOException {
        TheaterMovieEntity theaterMovie = repository.findByTheaterIdAndMovieId(theaterId, movieId);
        return convertToTheaterMovieDto(theaterMovie);
    }

    //Add method
    public TheaterMovieDTOOut addTheaterMovie(TheaterMovieDTOIn newTheaterMovie) throws IOException {
        MovieEntity movie = movieService.findById(newTheaterMovie.getMovieId());
        TheaterEntity theater = theaterService.findById(newTheaterMovie.getTheaterId());
        TheaterMovieEntity theaterMovie = new TheaterMovieEntity(
                newTheaterMovie.getStartDate(),
                newTheaterMovie.getEndDate(),
                newTheaterMovie.getStatus(),
                newTheaterMovie.getTheaterFav(),
                movie,
                theater
        );
        repository.save(theaterMovie);

        return convertToTheaterMovieDto(theaterMovie);
    }

    //Update method
    public TheaterMovieDTOOut updateTheaterMovie(Integer id, TheaterMovieDTOIn newTheaterMovie) throws IOException {
        TheaterMovieEntity theaterMovieToUpdate = repository.findById(id).orElseThrow(() -> new NotFoundException("theaterMovie ", id.toString()));

        theaterMovieToUpdate.setStartDate(newTheaterMovie.getStartDate());
        theaterMovieToUpdate.setEndDate(newTheaterMovie.getEndDate());
        theaterMovieToUpdate.setTheaterFav(newTheaterMovie.getTheaterFav());
        theaterMovieToUpdate.setStatus(newTheaterMovie.getStatus());
        theaterMovieToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
        repository.save(theaterMovieToUpdate);

        return convertToTheaterMovieDto(theaterMovieToUpdate);
    }

    //Delete method
    public void deleteTheaterMovie(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    public byte[] getFileFromPath(String imageName) throws IOException {
        File poster = new File("src/main/resources/uploads/" + imageName);
        byte[] bytes = Files.readAllBytes(poster.getAbsoluteFile().toPath());

        return bytes;
    };
}
