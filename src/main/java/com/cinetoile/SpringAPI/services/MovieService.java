package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.MovieDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.MovieDTOOut;
import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.models.TheaterMovieEntity;
import com.cinetoile.SpringAPI.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository repository;

    //Constructor
    MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    //ConvertToDto methods
    private MovieDTOOut convertToMovieDto(MovieEntity movie) {
        return new MovieDTOOut(
                movie.getId(),
                movie.getName(),
                movie.getDescription(),
                movie.getDuration(),
                movie.getTmdbKey(),
                movie.getComment(),
                movie.getRate(),
                movie.getImage(),
                movie.getLaunchDate(),
                movie.getDirector(),
                movie.getCasting(),
                movie.getAdvisedAge(),
                movie.getCountry()
        );
    }

    private List<MovieDTOOut> convertToListMovieDto(List<MovieEntity> list) {
        return list.stream()
                .map(movie -> new MovieDTOOut(
                        movie.getId(),
                        movie.getName(),
                        movie.getDescription(),
                        movie.getDuration(),
                        movie.getTmdbKey(),
                        movie.getComment(),
                        movie.getRate(),
                        movie.getImage(),
                        movie.getLaunchDate(),
                        movie.getDirector(),
                        movie.getCasting(),
                        movie.getAdvisedAge(),
                        movie.getCountry()
                ))
                .collect(Collectors.toList());
    }

    //Get methods
    public List<MovieDTOOut> findAll() {
        List<MovieEntity> list = repository.findAll();
        return convertToListMovieDto(list);
    }

    public MovieEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("movie", id.toString()));
    }

    public MovieDTOOut findDto(Integer id) {
        MovieEntity movie = repository.findById(id).orElseThrow(() -> new NotFoundException("movie", id.toString()));
        return convertToMovieDto(movie);
    }

    //Add method
    public MovieDTOOut add(MovieDTOIn newMovie) {
        MovieEntity movie = new MovieEntity(
                newMovie.getName(),
                newMovie.getDescription(),
                newMovie.getDuration(),
                newMovie.getTmdbKey(),
                newMovie.getComment(),
                newMovie.getRate(),
                newMovie.getImage(),
                newMovie.getLaunchDate(),
                newMovie.getDirector(),
                newMovie.getCasting(),
                newMovie.getAdvisedAge(),
                newMovie.getCountry()
        );

        repository.save(movie);
        return convertToMovieDto(movie);
    }

    //edit method
    public MovieDTOOut update(MovieEntity newMovie, Integer id) {
        return repository.findById(id).map(movie -> {
            movie.setName(newMovie.getName());
            movie.setDescription(newMovie.getDescription());
            movie.setDuration(newMovie.getDuration());
            movie.setTmdbKey(newMovie.getTmdbKey());
            movie.setComment(newMovie.getComment());
            movie.setRate(newMovie.getRate());
            movie.setImage(newMovie.getImage());
            movie.setLaunchDate(newMovie.getLaunchDate());
            movie.setDirector(newMovie.getDirector());
            movie.setCasting(newMovie.getCasting());
            movie.setAdvisedAge(newMovie.getAdvisedAge());
            movie.setCountry(newMovie.getCountry());

            repository.save(movie);

            return convertToMovieDto(movie);
        }).orElseGet(() -> {
            newMovie.setId(id);
            repository.save(newMovie);

            return convertToMovieDto(newMovie);
        });
    }

    //Delete method
    public void delete(Integer id) {repository.deleteById(id);}
}
