package com.backend.pascal.dto;

import com.backend.pascal.model.enums.AvailabilityStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Book creation requests.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Author is required")
    private String author;
    
    @NotBlank(message = "ISBN is required")
    private String isbn;
    
    @NotNull(message = "Availability status is required")
    private AvailabilityStatus availabilityStatus;
}