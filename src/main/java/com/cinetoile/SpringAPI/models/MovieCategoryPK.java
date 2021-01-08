package com.cinetoile.SpringAPI.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MovieCategoryPK implements Serializable {
    private int movieId;
    private int categoryId;

    @Column(name = "movieId", nullable = false)
    @Id
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Column(name = "categoryId", nullable = false)
    @Id
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
        MovieCategoryPK that = (MovieCategoryPK) o;
        return movieId == that.movieId &&
                categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, categoryId);
    }
}
