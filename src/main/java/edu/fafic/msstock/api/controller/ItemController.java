package edu.fafic.msstock.api.controller;

import edu.fafic.msstock.application.dto.ItemDTO;
import edu.fafic.msstock.infrastructure.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDTO> create(@RequestBody @Valid ItemDTO dto) {
        ItemDTO created = itemService.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ItemDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(itemService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> update(@PathVariable String id, @RequestBody @Valid ItemDTO dto) {
        return ResponseEntity.ok(itemService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
