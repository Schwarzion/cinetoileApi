package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.models.Category;
import com.cinetoile.SpringAPI.models.Movie;
import com.cinetoile.SpringAPI.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    List<Category> all() { return this.categoryService.findAll(); }

    @GetMapping("/category/{id}")
    Category one(@PathVariable Integer id) {
        return this.categoryService.find(id);
    }

    @PostMapping("/category")
    Category add(@RequestBody Category newCategory) {
        return this.categoryService.add(newCategory);
    }

    @PutMapping("category/{id}")
    Category update(@RequestBody Category newCategory, @PathVariable Integer id) {
        return this.categoryService.update(newCategory ,id) ;
    }

    @DeleteMapping("/category/{id}")
    void delete(@PathVariable Integer id) {
        this.categoryService.delete(id);
    }

    @GetMapping("/movies/category/{movieId}")
    List<Movie> getMovieCategories(@PathVariable Integer movieId) {
        Category category =  this.categoryService.find(movieId);
        System.out.println("trying to get categories");
        return category.getMovies();
    }
}
