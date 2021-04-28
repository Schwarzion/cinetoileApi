package com.cinetoile.SpringAPI.dto.dtoOut;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PricingDtoOut {
    Integer id;
    String name;
    String price;

    public PricingDtoOut(Integer id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
