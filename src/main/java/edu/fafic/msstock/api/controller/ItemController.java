package edu.fafic.msstock.api.controller;

import edu.fafic.msstock.application.dto.ItemDTO;
import edu.fafic.msstock.application.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Tag(name = "Items", description = "Operações CRUD para itens de estoque")
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "Cria um novo item")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "409", description = "Conflito", content = @Content),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = @Content),
    })
    @PostMapping
    public ResponseEntity<ItemDTO> create(@Valid @RequestBody ItemDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(dto));
    }

    @Operation(summary = "Busca item por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(itemService.findOr404(id));
    }

    @Operation(summary = "Lista itens")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @GetMapping
    public ResponseEntity<List<ItemDTO>> findAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @Operation(summary = "Atualiza um item por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado"),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflito", content = @Content),
            @ApiResponse(responseCode = "422", description = "Erro de validação", content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> update(@PathVariable String id, @RequestBody @Valid ItemDTO dto) {
        return ResponseEntity.ok(itemService.update(id, dto));
    }

    @Operation(summary = "Remove um item por id ")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sem conteúdo"),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
