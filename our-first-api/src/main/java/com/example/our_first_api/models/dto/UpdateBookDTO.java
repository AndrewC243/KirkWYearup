package com.example.our_first_api.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookDTO {
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title cannot be longer than 100 characters")
    private String title;
    @NotBlank
    @Size(max = 100, message = "Author name cannot be longer than 100 characters")
    private String author;
    @NotBlank(message = "ISBN may not be blank")
    @Pattern(regexp = "(\\d{3}-?)?\\d-?\\d{3}-?\\d{5}-?\\d", message = "Invalid ISBN")
    private String isbn;
}
