package com.example.our_first_api.models.mapper;

import com.example.our_first_api.models.Recipe;
import com.example.our_first_api.models.dto.CreateRecipeDTO;
import com.example.our_first_api.models.dto.RecipeDTO;

public class RecipeMapper {
    public static RecipeDTO toDTO(Recipe recipe) {
        RecipeDTO dto = new RecipeDTO();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setInstructions(recipe.getInstructions());
        dto.setMinutesToCook(recipe.getMinutesToCook());
        return dto;
    }

    public static Recipe toEntity(RecipeDTO dto) {
        Recipe entity = new Recipe();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setInstructions(dto.getInstructions());
        entity.setMinutesToCook(dto.getMinutesToCook());
        return entity;
    }

    public static Recipe toEntity(CreateRecipeDTO dto) {
        Recipe recipe = new Recipe();
        recipe.setName(dto.getName());
        recipe.setInstructions(dto.getInstructions());
        recipe.setMinutesToCook(dto.getMinutesToCook());
        return recipe;
    }
}
