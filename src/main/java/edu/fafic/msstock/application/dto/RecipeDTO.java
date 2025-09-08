package edu.fafic.msstock.application.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "Receita", description = "Documento da receita de um cardápio")
public class RecipeDTO {

    @Schema(description = "Identificador da receita", example = "68be41b996833d950c093974")
    private String id;

    @NotNull(message = "O campo 'menuId' é obrigatório")
    @Schema(description = "Identificador do cardápio", example = "68be41c37a950ef3e44f9194")
    private String menuId;

    @NotEmpty(message = "O campo 'ingredients' não pode estar vazio")
    @ArraySchema(
            schema = @Schema(implementation = IngredientDTO.class),
            arraySchema = @Schema(description = "Lista de ingredientes"),
            minItems = 1
    )
    private List<@Valid IngredientDTO> ingredients;

}
