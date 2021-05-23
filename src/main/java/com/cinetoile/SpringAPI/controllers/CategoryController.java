package com.cinetoile.SpringAPI.controllers;

import com.cinetoile.SpringAPI.models.CategoryEntity;
import com.cinetoile.SpringAPI.models.Movie;
import com.cinetoile.SpringAPI.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    List<CategoryEntity> all() { return this.categoryService.findAll(); }

    @GetMapping("/category/{id}")
    CategoryEntity one(@PathVariable Integer id) {
        return this.categoryService.find(id);
    }

    @PostMapping("/category")
    CategoryEntity add(@RequestBody CategoryEntity newCategory) {
        return this.categoryService.add(newCategory);
    }

    @PutMapping("category/{id}")
    CategoryEntity update(@RequestBody CategoryEntity newCategory, @PathVariable Integer id) {
        return this.categoryService.update(newCategory ,id);
    }

    @DeleteMapping("/category/{id}")
    void delete(@PathVariable Integer id) {
        this.categoryService.delete(id);
    }

    @GetMapping("/category/movie/{id}")
    Set<Movie> movies(@PathVariable Integer id) {
        CategoryEntity cat = this.one(id);
        return cat.getMovies();
    }
}
