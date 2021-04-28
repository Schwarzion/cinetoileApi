package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.CategoryEntity;
import com.cinetoile.SpringAPI.models.PricingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingRepository extends JpaRepository<PricingEntity, Integer> {

}
