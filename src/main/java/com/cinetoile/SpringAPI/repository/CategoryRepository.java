package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
