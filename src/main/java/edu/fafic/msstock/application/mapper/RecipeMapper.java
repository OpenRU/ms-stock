package edu.fafic.msstock.application.mapper;

import edu.fafic.msstock.application.dto.RecipeDTO;
import edu.fafic.msstock.domain.Recipe;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {IngredientMapper.class})
public interface RecipeMapper {

    RecipeDTO toDTO(Recipe recipe);

    Recipe toEntity(RecipeDTO recipeDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(RecipeDTO recipeDTO, @MappingTarget Recipe recipe);
}
