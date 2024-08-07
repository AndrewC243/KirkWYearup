package com.example.our_first_api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private long id;
    private String name;
    private String instructions;
    private int minutesToCook;
}
