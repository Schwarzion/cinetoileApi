package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.MovieDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.MovieDTOOut;
import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<MovieEntity> findAll() { return repository.findAll();}

    public MovieEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("movie", id.toString()));
    }

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

    public MovieEntity update(MovieEntity newMovie, Integer id) {
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

            return repository.save(movie);
        }).orElseGet(() -> {
            newMovie.setId(id);
            return repository.save(newMovie);
        });
    }

    public void delete(Integer id) { repository.deleteById(id);}
}
