package com.backend.pascal.dto;

import com.backend.pascal.model.enums.AvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Book responses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
    
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private AvailabilityStatus availabilityStatus;
}