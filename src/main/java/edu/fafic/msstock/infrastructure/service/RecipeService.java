package edu.fafic.msstock.infrastructure.service;

import edu.fafic.msstock.api.error.NotFoundException;
import edu.fafic.msstock.application.dto.RecipeDTO;
import edu.fafic.msstock.application.mapper.RecipeMapper;
import edu.fafic.msstock.domain.Recipe;
import edu.fafic.msstock.infrastructure.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeMapper recipeMapper;

    @Transactional
    public RecipeDTO create(RecipeDTO dto) {
        Recipe entity = recipeMapper.toEntity(dto);
        Recipe saved = recipeRepository.save(entity);
        return recipeMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public RecipeDTO findById(String id) {
        Recipe entity = getEntityOr404(id);
        return recipeMapper.toDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<RecipeDTO> findAll(Pageable pageable) {
        return recipeRepository.findAll(pageable).map(recipeMapper::toDTO);
    }

    @Transactional
    public RecipeDTO update(String id, RecipeDTO dto) {
        Recipe existing = getEntityOr404(id);
        recipeMapper.update(dto, existing);
        Recipe saved = recipeRepository.save(existing);
        return recipeMapper.toDTO(saved);
    }

    @Transactional
    public void delete(String id) {
        Recipe existing = getEntityOr404(id);
        recipeRepository.delete(existing);
    }

    private Recipe getEntityOr404(String id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe não encontrado: id=" + id));
    }
}
