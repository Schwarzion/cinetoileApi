package com.cinetoile.SpringAPI.dto.dtoOut;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTOOut {
    Integer id;
    String name;

    public CategoryDTOOut(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}