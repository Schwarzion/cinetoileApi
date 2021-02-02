package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.models.Category;
import com.cinetoile.SpringAPI.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    CategoryService(CategoryRepository repository) { this.repository = repository;}

    public List<Category> findAll() { return repository.findAll();}

    public Category find(Integer id) { return repository.findById(id).orElseThrow(() -> new NotFoundException("reservation ", id)); }

    public Category add(Category newCategory) {
        return repository.save(newCategory);
    }

    public Category update(Category newCategory, Integer id) {
        return repository.findById(id).map(category -> {
            category.setName(newCategory.getName());
            return repository.save(category);
        }).orElseGet(() -> {
            newCategory.setId(id);
            return repository.save(newCategory);
        });
    }
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

