package edu.fafic.msstock.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MeasurementUnit {

    KILOGRAM("kg"),
    GRAM("g"),
    MILLIGRAM("mg"),
    MILLILITER("ml"),
    LITER("l"),
    UNIT("un"),
    TABLESPOON("tbsp"),
    TEASPOON("tsp"),
    CUP("cup");

    private final String symbol;

}
