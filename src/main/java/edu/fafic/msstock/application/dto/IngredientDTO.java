package edu.fafic.msstock.application.dto;

import edu.fafic.msstock.shared.enums.MeasurementUnit;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "Ingredient", description = "Subschema de Ingredient em Recipe")
public class IngredientDTO {

    @NotBlank(message = "O campo 'name' é obrigatório")
    @Schema(description = "Nome do ingredient", example = "Tomate")
    private String name;

    @Min(value = 1, message = "O campo 'quantity' deve ser maior ou igual a 1")
    @Schema(description = "Quantidade disponível do ingredient", example = "2", minimum = "1")
    private int quantity;

    @NotNull(message = "O campo 'measurementUnit' é obrigatória")
    @Schema(description = "Unidade de medida do ingredient", example = "UNIT")
    private MeasurementUnit measurementUnit;

}
