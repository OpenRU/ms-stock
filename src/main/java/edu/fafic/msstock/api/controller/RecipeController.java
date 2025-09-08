package edu.fafic.msstock.api.controller;

import edu.fafic.msstock.application.dto.RecipeDTO;
import edu.fafic.msstock.infrastructure.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeDTO> create(@RequestBody @Valid RecipeDTO dto) {
        RecipeDTO created = recipeService.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(recipeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<RecipeDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(recipeService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> update(@PathVariable String id, @RequestBody @Valid RecipeDTO dto) {
        return ResponseEntity.ok(recipeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        recipeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
