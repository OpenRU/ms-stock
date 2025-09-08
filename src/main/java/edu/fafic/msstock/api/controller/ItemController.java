package edu.fafic.msstock.api.controller;

import edu.fafic.msstock.application.dto.ItemDTO;
import edu.fafic.msstock.infrastructure.service.ItemService;
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
@RequestMapping("/items")
@RequiredArgsConstructor
@Tag(name = "Items", description = "Operações CRUD para itens de estoque")
public class ItemController {

    private final ItemService itemService;

    @Operation(
            summary = "Cria um novo item",
            description = "Retorna o item criado e define o header Location com a URL do recurso"
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
    public ResponseEntity<ItemDTO> create(@RequestBody @Valid ItemDTO dto) {
        ItemDTO created = itemService.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(summary = "Busca item por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @Operation(
            summary = "Lista itens com paginação",
            description = "Suporta paginação via parâmetros padrão: page, size, sort"
    )
    @GetMapping
    public ResponseEntity<Page<ItemDTO>> findAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(itemService.findAll(pageable));
    }

    @Operation(summary = "Atualiza um item por ID")
    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> update(@PathVariable String id, @RequestBody @Valid ItemDTO dto) {
        return ResponseEntity.ok(itemService.update(id, dto));
    }

    @Operation(summary = "Remove um item por ID")
    @ApiResponse(responseCode = "204", description = "Sem conteúdo", useReturnTypeSchema = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
