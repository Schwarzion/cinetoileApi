package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.models.Movie;
import com.cinetoile.SpringAPI.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> findAll() { return repository.findAll();}

    public Movie findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("movie", id.toString()));
    }

    public Movie add(Movie newMovie) { return repository.save(newMovie);}

    public Movie update(Movie newMovie, Integer id) {
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
