package edu.fafic.msstock.shared.service;

import edu.fafic.msstock.domain.Ingredient;
import edu.fafic.msstock.domain.Item;
import edu.fafic.msstock.shared.enums.MeasurementUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final MongoTemplate mongoTemplate;

    private static Criteria where(String name, MeasurementUnit unit) {
        return Criteria.where("name").is(name)
                .and("measurementUnit").is(unit);
    }

    public boolean exists(Ingredient ingredient) {
        Criteria criteria = where(ingredient.getName(), ingredient.getMeasurementUnit());
        return mongoTemplate.exists(Query.query(criteria), Item.class);
    }

    public Item find(Ingredient ingredient) {
        Criteria criteria = where(ingredient.getName(), ingredient.getMeasurementUnit());
        return mongoTemplate.findOne(Query.query(criteria), Item.class);
    }

    public void consume(Ingredient ingredient, int needed) {
        Item item = find(ingredient);
        item.setQuantity(item.getQuantity() - ingredient.getQuantity() * needed);
        mongoTemplate.save(item);
    }
}
