package com.cinetoile.SpringAPI.repository;

import com.cinetoile.SpringAPI.models.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    List<RoomEntity> findAllByTheaterId(Integer id);
}
