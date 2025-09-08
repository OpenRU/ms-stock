package edu.fafic.msstock.domain;

import edu.fafic.msstock.shared.enums.MeasurementUnit;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private String name;

    private int quantity;

    private MeasurementUnit measurementUnit;

}
