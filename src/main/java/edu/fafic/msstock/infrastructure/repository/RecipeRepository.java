package edu.fafic.msstock.infrastructure.repository;

import edu.fafic.msstock.domain.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

    Optional<Recipe> findByMenuId(String menuId);
}
