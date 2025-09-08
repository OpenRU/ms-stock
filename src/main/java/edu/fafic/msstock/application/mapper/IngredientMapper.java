package edu.fafic.msstock.application.mapper;

import edu.fafic.msstock.application.dto.IngredientDTO;
import edu.fafic.msstock.domain.Ingredient;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientDTO toDTO(Ingredient ingredient);

    Ingredient toEntity(IngredientDTO ingredientDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(IngredientDTO ingredientDTO, @MappingTarget Ingredient ingredient);
}

