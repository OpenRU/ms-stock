package edu.fafic.msstock.application.mapper;

import edu.fafic.msstock.application.dto.RecipeDTO;
import edu.fafic.msstock.domain.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {IngredientMapper.class, ItemMapper.class})
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDTO toDTO(Recipe recipe);

    Recipe toEntity(RecipeDTO recipeDTO);
}
