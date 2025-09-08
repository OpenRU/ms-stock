package edu.fafic.msstock.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO {

    private String id;

    @NotNull(message = "O campo 'menuId' é obrigatório")
    private String menuId;

    @NotEmpty(message = "O campo 'ingredients' não pode estar vazio")
    private List<@Valid IngredientDTO> ingredients;

}
