package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.models.CategoryEntity;
import com.cinetoile.SpringAPI.repository.CategoryRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    CategoryService(CategoryRepository repository) { this.repository = repository;}

    public List<CategoryEntity> findAll() { return repository.findAll();}

    public CategoryEntity find(Integer id) { return repository.findById(id).orElseThrow(() -> new NotFoundException("category ", id.toString())); }

    public CategoryEntity add(CategoryEntity newCategory) {
        return repository.save(newCategory);
    }

    public CategoryEntity update(CategoryEntity newCategory, Integer id) {
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

