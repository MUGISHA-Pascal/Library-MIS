package com.backend.pascal.dto;

import com.backend.pascal.model.enums.BorrowingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for BorrowingTransaction responses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingTransactionResponseDTO {
    
    private Long id;
    private BookResponseDTO book;
    private String borrowerName;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private BorrowingStatus status;
}