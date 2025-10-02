package edu.fafic.msstock.application.service;

import edu.fafic.msstock.application.dto.ItemDTO;
import edu.fafic.msstock.application.mapper.ItemMapper;
import edu.fafic.msstock.application.repository.ItemRepository;
import edu.fafic.msstock.domain.Ingredient;
import edu.fafic.msstock.domain.Item;
import edu.fafic.msstock.shared.error.ConflictException;
import edu.fafic.msstock.shared.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    private final MongoTemplate mongoTemplate;

    public ItemDTO create(ItemDTO dto) {
        if (itemRepository.existsByNameAndSupplier(dto.getName(), dto.getSupplier())) {
            throw new ConflictException("Já existe um item com esse nome e fornecedor");
        }

        Item entity = itemMapper.toEntity(dto);
        Item saved = itemRepository.save(entity);
        return itemMapper.toDTO(saved);
    }

    public Optional<ItemDTO> findById(String id) {
        Optional<Item> optional = itemRepository.findById(id);
        return optional.map(itemMapper::toDTO);
    }

    public ItemDTO findOr404(String id) {
        Item entity = getEntityOr404(id);
        return itemMapper.toDTO(entity);
    }

    public List<ItemDTO> findAll() {
        return itemRepository.findAll().stream().map(itemMapper::toDTO).toList();
    }

    public ItemDTO update(String id, ItemDTO dto) {
        Item existing = getEntityOr404(id);
        itemMapper.update(dto, existing);
        Item saved = itemRepository.save(existing);
        return itemMapper.toDTO(saved);
    }

    public void delete(String id) {
        Item existing = getEntityOr404(id);
        itemRepository.delete(existing);
    }

    private Item getEntityOr404(String id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item não encontrado"));
    }
}
