package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.MovieDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.MovieDTOOut;
import com.cinetoile.SpringAPI.models.MovieEntity;
import com.cinetoile.SpringAPI.repository.MovieRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private HttpServletRequest request;

    private final MovieRepository repository;

    private String uploadsPath = "src/main/resources/uploads/";
    private String path = Paths.get("/" + uploadsPath).toAbsolutePath().toString();

    //Constructor
    MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    //ConvertToDto methods
    private MovieDTOOut convertToMovieDto(MovieEntity movie) throws IOException {
        return new MovieDTOOut(
                movie.getId(),
                movie.getName(),
                movie.getDescription(),
                movie.getDuration(),
                movie.getTmdbKey(),
                movie.getComment(),
                movie.getRate(),
                getFileFromPath(movie.getImage()),
                movie.getLaunchDate(),
                movie.getDirector(),
                movie.getCasting(),
                movie.getAdvisedAge(),
                movie.getCountry()
        );
    }

    private List<MovieDTOOut> convertToListMovieDto(List<MovieEntity> list) {
        return list.stream()
                .map(movie -> {
                    try {
                        return new MovieDTOOut(
                                movie.getId(),
                                movie.getName(),
                                movie.getDescription(),
                                movie.getDuration(),
                                movie.getTmdbKey(),
                                movie.getComment(),
                                movie.getRate(),
                                getFileFromPath(movie.getImage()),
                                movie.getLaunchDate(),
                                movie.getDirector(),
                                movie.getCasting(),
                                movie.getAdvisedAge(),
                                movie.getCountry()
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
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

    public MovieDTOOut findDto(Integer id) throws IOException {
        MovieEntity movie = repository.findById(id).orElseThrow(() -> new NotFoundException("movie", id.toString()));
        return convertToMovieDto(movie);
    }

    //Add method
    public MovieDTOOut add(MovieDTOIn newMovie) throws IOException {
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

            try {
                return convertToMovieDto(movie);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).orElseGet(() -> {
            newMovie.setId(id);
            repository.save(newMovie);

            try {
                return convertToMovieDto(newMovie);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    //Delete method
    public void delete(Integer id) {repository.deleteById(id);}

    //Upload poster method
    public String uploadPoster(MultipartFile moviePoster) throws IOException {
        //Get path to uploads folder
        String filePath = Paths.get(uploadsPath).toAbsolutePath().toString();

        //Create new file
        File dest = new File(filePath + "/" + moviePoster.getOriginalFilename());

        //Move moviePoster to new file
        moviePoster.transferTo(dest);

        //return name of moviePoster file
        return moviePoster.getOriginalFilename();
    };

    public byte[] getFileFromPath(String imageName) throws IOException {
        File poster = new File("src/main/resources/uploads/" + imageName);
        byte[] bytes = Files.readAllBytes(poster.getAbsoluteFile().toPath());

        return bytes;
    };
}
