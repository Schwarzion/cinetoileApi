package com.cinetoile.SpringAPI.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Movie_Category", schema = "cinetoile", catalog = "")
@IdClass(MovieCategoryPK.class)
public class MovieCategory {
    private int movieId;
    private int categoryId;

    @Id
    @Column(name = "movieId", nullable = false)
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Id
    @Column(name = "categoryId", nullable = false)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieCategory that = (MovieCategory) o;
        return movieId == that.movieId &&
                categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, categoryId);
    }
}
