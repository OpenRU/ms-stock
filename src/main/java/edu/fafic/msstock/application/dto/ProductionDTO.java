package edu.fafic.msstock.application.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "Production", description = "Resultado da produção de uma receita")
public class ProductionDTO {

    @Schema(description = "Indica se a receita foi produzida", example = "true")
    boolean served;

    @Schema(description = "Quantidade solicitada para a receita", example = "12")
    int quantity;

    @Schema(description = "Receita utilizada na produção", implementation = RecipeDTO.class)
    RecipeDTO recipe;

    @ArraySchema(
            arraySchema = @Schema(description = "Lista de ingredientes que estavam em falta para atender a quantidade solicitada"),
            schema = @Schema(implementation = IngredientDTO.class)
    )
    private List<IngredientDTO> lacking;
}
