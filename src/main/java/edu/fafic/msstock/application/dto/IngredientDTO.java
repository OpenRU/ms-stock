package edu.fafic.msstock.application.dto;

import edu.fafic.msstock.shared.enums.MeasurementUnit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IngredientDTO {

    @NotBlank(message = "O campo 'name' é obrigatório")
    private String name;

    @Min(value = 1, message = "O campo 'quantity' deve ser maior ou igual a 1")
    private int quantity;

    @NotNull(message = "O campo 'measurementUnit' é obrigatória")
    private MeasurementUnit measurementUnit;

}
