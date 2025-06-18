package com.backend.pascal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for BorrowingTransaction creation requests.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingTransactionRequestDTO {
    
    @NotBlank(message = "ISBN is required")
    private String isbn;
    
    @NotBlank(message = "Borrower name is required")
    private String borrowerName;
    
    @NotNull(message = "Borrow date is required")
    private LocalDateTime borrowDate;
}