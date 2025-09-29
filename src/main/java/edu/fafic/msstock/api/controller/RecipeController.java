package edu.fafic.msstock.api.controller;

import edu.fafic.msstock.application.dto.RecipeDTO;
import edu.fafic.msstock.infrastructure.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
@Tag(name = "Recipes", description = "Operações CRUD para receitas de um cardápio")
public class RecipeController {

    private final RecipeService recipeService;

    @Operation(summary = "Cria uma nova receita")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "409", description = "Conflito", content = @Content),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = @Content),
    })
    @PostMapping
    public ResponseEntity<RecipeDTO> create(@Valid @RequestBody RecipeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.create(dto));
    }

    @Operation(summary = "Busca receita por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(recipeService.findOr404(id));
    }

    @Operation(summary = "Busca receita por menuId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
    })
    @GetMapping("/by-menu/{menuId}")
    public ResponseEntity<RecipeDTO> findByMenuId(@PathVariable String menuId) {
        return ResponseEntity.ok(recipeService.findByMenuId(menuId));
    }

    @Operation(
            summary = "Lista receitas com paginação",
            description = "Suporta paginação via parâmetros: page, size, sort"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @GetMapping
    public ResponseEntity<Page<RecipeDTO>> findAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(recipeService.findAll(pageable));
    }

    @Operation(summary = "Atualiza uma receita por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado"),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflito", content = @Content),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> update(@PathVariable String id, @RequestBody @Valid RecipeDTO dto) {
        return ResponseEntity.ok(recipeService.update(id, dto));
    }

    @Operation(summary = "Remove uma receita por id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sem conteúdo", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        recipeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
