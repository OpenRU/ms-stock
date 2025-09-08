package edu.fafic.msstock.application.dto;

import edu.fafic.msstock.shared.enums.MeasurementUnit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemDTO {

    private String id;

    @NotBlank(message = "O campo 'name' é obrigatório")
    private String name;

    @Min(value = 0, message = "O campo 'quantity' não pode ser negativo")
    private int quantity;

    @NotNull(message = "O campo 'measurementUnit' é obrigatório")
    private MeasurementUnit measurementUnit;
    
    @NotNull(message = "O campo 'supplier' é obrigatório")
    private String supplier;

}
