package edu.fafic.msstock.application.dto;

import edu.fafic.msstock.shared.enums.MeasurementUnit;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "Item", description = "Documento do item no estoque")
public class ItemDTO {

    @Schema(description = "Identificador do item", example = "64f1b2c3d4e5f6a7b8c9d0e1")
    private String id;

    @NotBlank(message = "O campo 'name' é obrigatório")
    @Schema(description = "Nome do item", example = "Café Torrado")
    private String name;

    @Min(value = 0, message = "O campo 'quantity' não pode ser negativo")
    @Schema(description = "Quantidade disponível do item", example = "42")
    private int quantity;

    @NotNull(message = "O campo 'measurementUnit' é obrigatório")
    @Schema(description = "Unidade de medida do item", example = "KILOGRAM")
    private MeasurementUnit measurementUnit;

    @NotNull(message = "O campo 'supplier' é obrigatório")
    @Schema(description = "Provedor do item", example = "Sousa Parafusos")
    private String supplier;

}
