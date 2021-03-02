package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.List;
=======
import java.util.*;
>>>>>>> f62b724 (trying to make relationship)
=======
import java.util.*;
>>>>>>> 78fd3b4b26cc2f6ba67334e0db96c4e76f28cf51

@Entity
@Data
@Table(name="Movie")
@NoArgsConstructor
public class Movie {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 120)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 300)
    private String description;
    @Basic
    @Column(name = "duration", nullable = false, length = 5)
    private String duration;
    @Basic
    @Column(name = "tmdbKey", nullable = true, length = 36)
    private String tmdbKey;
    @Basic
    @Column(name = "comment", nullable = true, length = 300)
    private String comment;
    @Basic
    @Column(name = "rate", nullable = true)
    private Integer rate;
    @Basic
    @Column(name = "image", nullable = true)
    private byte[] image;
    @Basic
    @Column(name = "launchDate", nullable = false)
    private Timestamp launchDate;
    @Basic
    @Column(name = "director", nullable = false, length = 100)
    private String director;
    @Basic
    @Column(name = "casting", nullable = false, length = 300)
    private String casting;
    @Basic
    @Column(name = "advisedAge", nullable = true)
    private Integer advisedAge;
    @Basic
    @Column(name = "country", nullable = true, length = 100)
    private String country;
    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

<<<<<<< HEAD
    @OneToMany(mappedBy = "id.movie", targetEntity = UserReviewMovie.class, fetch = FetchType.EAGER)
    public List<UserReviewMovie> userReviewMovies;
=======
    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, description, duration, tmdbKey, comment, rate, launchDate, director, casting, advisedAge, country, createdAt, updatedAt);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
<<<<<<< HEAD

    @ManyToMany
    @JoinTable(
            name = "Movie_Category",
            joinColumns = {@JoinColumn(name = "movieId")},
            inverseJoinColumns = {@JoinColumn(name = "categoryId")})
    Set<Category> movieCategories;
>>>>>>> f62b724 (trying to make relationship)
=======
>>>>>>> 78fd3b4 (relation columns)
}
