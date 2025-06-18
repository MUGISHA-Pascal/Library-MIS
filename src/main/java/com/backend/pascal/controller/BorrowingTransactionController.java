package com.backend.pascal.controller;

import com.backend.pascal.dto.BorrowingTransactionRequestDTO;
import com.backend.pascal.dto.BorrowingTransactionResponseDTO;
import com.backend.pascal.service.BorrowingTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing borrowing transactions.
 */
@RestController
@RequestMapping("/api/borrowing-transactions")
public class BorrowingTransactionController {
    
    private final BorrowingTransactionService borrowingTransactionService;
    
    @Autowired
    public BorrowingTransactionController(BorrowingTransactionService borrowingTransactionService) {
        this.borrowingTransactionService = borrowingTransactionService;
    }
    
    /**
     * Create a new borrowing transaction.
     *
     * @param borrowingTransactionRequestDTO the borrowing transaction request DTO
     * @return the created borrowing transaction response DTO
     */
    @PostMapping
    public ResponseEntity<BorrowingTransactionResponseDTO> createBorrowingTransaction(
            @Valid @RequestBody BorrowingTransactionRequestDTO borrowingTransactionRequestDTO) {
        BorrowingTransactionResponseDTO createdTransaction = 
                borrowingTransactionService.createBorrowingTransaction(borrowingTransactionRequestDTO);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }
    
    /**
     * Return a borrowed book.
     *
     * @param transactionId the ID of the borrowing transaction
     * @return the updated borrowing transaction response DTO
     */
    @PutMapping("/{transactionId}/return")
    public ResponseEntity<BorrowingTransactionResponseDTO> returnBook(@PathVariable Long transactionId) {
        BorrowingTransactionResponseDTO updatedTransaction = borrowingTransactionService.returnBook(transactionId);
        return ResponseEntity.ok(updatedTransaction);
    }
}