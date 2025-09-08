package edu.fafic.msstock.application.mapper;

import edu.fafic.msstock.application.dto.ItemDTO;
import edu.fafic.msstock.domain.Item;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDTO toDTO(Item item);

    Item toEntity(ItemDTO itemDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(ItemDTO itemDTO, @MappingTarget Item item);
}
