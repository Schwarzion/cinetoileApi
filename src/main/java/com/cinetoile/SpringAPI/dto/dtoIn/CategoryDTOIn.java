package com.cinetoile.SpringAPI.dto.dtoIn;

import lombok.Data;

@Data
public class CategoryDTOIn {
    Integer id;
    String name;

    public CategoryDTOIn() {
    }

    public CategoryDTOIn(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}