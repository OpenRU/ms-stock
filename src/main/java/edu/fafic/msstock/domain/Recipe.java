package edu.fafic.msstock.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collation = "recipes")
public class Recipe {

    @Id
    private String id;

    private String menuId;

    private List<Ingredient> ingredients;
}
