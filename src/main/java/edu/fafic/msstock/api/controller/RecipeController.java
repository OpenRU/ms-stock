package edu.fafic.msstock.api.controller;

import edu.fafic.msstock.application.dto.RecipeDTO;
import edu.fafic.msstock.infrastructure.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
@Tag(name = "Recipes", description = "Operações CRUD para receitas de um cardápio")
public class RecipeController {

    private final RecipeService recipeService;

    @Operation(
            summary = "Cria uma nova receita",
            description = "Retorna a receita criada e define o header Location com a URL do recurso"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Criado",
            headers = {
                    @Header(name = "Location", description = "URL do recurso criado")
            },
            useReturnTypeSchema = true
    )
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

    @Operation(summary = "Busca receita por ID")
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(recipeService.findById(id));
    }

    @Operation(
            summary = "Lista receitas com paginação",
            description = "Suporta paginação via parâmetros padrão: page, size, sort"
    )
    @GetMapping
    public ResponseEntity<Page<RecipeDTO>> findAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(recipeService.findAll(pageable));
    }

    @Operation(summary = "Atualiza uma receita por ID")
    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> update(@PathVariable String id, @RequestBody @Valid RecipeDTO dto) {
        return ResponseEntity.ok(recipeService.update(id, dto));
    }

    @Operation(summary = "Remove uma receita por ID")
    @ApiResponse(responseCode = "204", description = "Sem conteúdo", useReturnTypeSchema = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        recipeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
