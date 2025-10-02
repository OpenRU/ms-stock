package edu.fafic.msstock.domain;

import edu.fafic.msstock.shared.enums.MeasurementUnit;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "items")
public class Item {

    private String id;

    private String name;

    private int quantity;

    private MeasurementUnit measurementUnit;

    private String supplier;

}
