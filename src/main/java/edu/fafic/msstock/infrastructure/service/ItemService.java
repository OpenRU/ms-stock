package edu.fafic.msstock.infrastructure.service;

import edu.fafic.msstock.application.dto.ItemDTO;
import edu.fafic.msstock.application.error.NotFoundException;
import edu.fafic.msstock.application.mapper.ItemMapper;
import edu.fafic.msstock.domain.Item;
import edu.fafic.msstock.infrastructure.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    @Transactional
    public ItemDTO create(ItemDTO dto) {
        Item entity = itemMapper.toEntity(dto);
        Item saved = itemRepository.save(entity);
        return itemMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public ItemDTO findById(String id) {
        Item entity = getEntityOr404(id);
        return itemMapper.toDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable).map(itemMapper::toDTO);
    }

    @Transactional
    public ItemDTO update(String id, ItemDTO dto) {
        Item existing = getEntityOr404(id);
        itemMapper.update(dto, existing);
        Item saved = itemRepository.save(existing);
        return itemMapper.toDTO(saved);
    }

    @Transactional
    public void delete(String id) {
        Item existing = getEntityOr404(id);
        itemRepository.delete(existing);
    }

    private Item getEntityOr404(String id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item não encontrado: id=" + id));
    }
}
