package edu.fafic.msstock.application.mapper;

import edu.fafic.msstock.application.dto.ItemDTO;
import edu.fafic.msstock.domain.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemDTO toDTO(Item item);

    Item toEntity(ItemDTO itemDTO);
}
