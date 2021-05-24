package com.cinetoile.SpringAPI.dto.dtoIn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PricingDtoIn {
    String name;
    String price;

    public PricingDtoIn(Integer id, String name, String price) {
        this.name = name;
        this.price = price;
    }
}
