package com.example.our_first_api.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecipeDTO {
    @NotBlank(message = "Name is mandatory")
    @Size(message = "Name can only be 100 characters long", max = 100)
    private String name;
    @NotBlank(message = "Must have instructions")
    private String instructions;
    @NotBlank(message = "Must have minutes to cook")
    @Min(value = 1, message = "Cooking time must be at least 1 minute")
    private int minutesToCook;
}
