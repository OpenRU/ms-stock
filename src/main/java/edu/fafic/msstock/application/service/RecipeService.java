package edu.fafic.msstock.application.service;

import edu.fafic.msstock.application.dto.IngredientDTO;
import edu.fafic.msstock.application.dto.ProductionDTO;
import edu.fafic.msstock.application.dto.RecipeDTO;
import edu.fafic.msstock.application.mapper.IngredientMapper;
import edu.fafic.msstock.application.mapper.RecipeMapper;
import edu.fafic.msstock.application.repository.RecipeRepository;
import edu.fafic.msstock.domain.Ingredient;
import edu.fafic.msstock.domain.Recipe;
import edu.fafic.msstock.shared.error.ConflictException;
import edu.fafic.msstock.shared.error.NotFoundException;
import edu.fafic.msstock.shared.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeMapper recipeMapper;

    private final IngredientService ingredientService;

    private final IngredientMapper ingredientMapper;

    private final MongoTemplate mongoTemplate;

    public RecipeDTO create(RecipeDTO dto) {
        Optional<Recipe> existing = recipeRepository.findByMenuId(dto.getMenuId());
        if (existing.isPresent()) {
            throw new ConflictException("Já existe uma receita para esse menu");
        }

        Recipe entity = recipeMapper.toEntity(dto);
        Recipe saved = recipeRepository.save(entity);
        return recipeMapper.toDTO(saved);
    }

    public Optional<RecipeDTO> findById(String id) {
        Optional<Recipe> optional = recipeRepository.findById(id);
        return optional.map(recipeMapper::toDTO);
    }

    public RecipeDTO findOr404(String id) {
        Recipe entity = getEntityOr404(id);
        return recipeMapper.toDTO(entity);
    }

    public RecipeDTO findByMenuId(String menuId) {
        Recipe entity = getByMenuIdOr404(menuId);
        return recipeMapper.toDTO(entity);
    }

    public List<RecipeDTO> findAll() {
        return recipeRepository.findAll().stream().map(recipeMapper::toDTO).toList();
    }

    public RecipeDTO update(String id, RecipeDTO dto) {
        Recipe existing = getEntityOr404(id);
        recipeMapper.update(dto, existing);
        Recipe saved = recipeRepository.save(existing);
        return recipeMapper.toDTO(saved);
    }

    public void delete(String id) {
        Recipe existing = getEntityOr404(id);
        recipeRepository.delete(existing);
    }

    private Recipe getByMenuIdOr404(String menuId) {
        return recipeRepository.findByMenuId(menuId)
                .orElseThrow(() -> new NotFoundException("Recipe não encontrada"));
    }

    private Recipe getEntityOr404(String id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe não encontrado"));
    }

    public ProductionDTO serve(String id, int needed) {
        Recipe recipe = getEntityOr404(id);
        boolean served = false;

        List<Ingredient> ingredients = recipe.getIngredients();

        List<Ingredient> lacking = ingredients.stream()
                .filter(in -> !ingredientService.exists(in))
                .toList();

        boolean hasEnough = ingredients.stream()
                .allMatch(in -> ingredientService.find(in).getQuantity() >= in.getQuantity() * needed);

        if (lacking.isEmpty() && hasEnough) {
            ingredients.forEach(in -> ingredientService.consume(in, needed));
            served = true;
        }

        List<IngredientDTO> lackingDTO = lacking.stream()
                .map(ingredientMapper::toDTO)
                .toList();

        ProductionDTO dto = new ProductionDTO();
        dto.setServed(served);
        dto.setQuantity(needed);
        dto.setRecipe(recipeMapper.toDTO(recipe));
        dto.setLacking(lackingDTO);

        return dto;
    }
}
