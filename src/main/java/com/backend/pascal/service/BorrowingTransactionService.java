package com.backend.pascal.service;

import com.backend.pascal.dto.BorrowingTransactionRequestDTO;
import com.backend.pascal.dto.BorrowingTransactionResponseDTO;

/**
 * Service interface for managing borrowing transactions.
 */
public interface BorrowingTransactionService {
    
    /**
     * Create a new borrowing transaction.
     *
     * @param borrowingTransactionRequestDTO the borrowing transaction request DTO
     * @return the created borrowing transaction response DTO
     */
    BorrowingTransactionResponseDTO createBorrowingTransaction(BorrowingTransactionRequestDTO borrowingTransactionRequestDTO);
    
    /**
     * Return a borrowed book.
     *
     * @param transactionId the ID of the borrowing transaction
     * @return the updated borrowing transaction response DTO
     */
    BorrowingTransactionResponseDTO returnBook(Long transactionId);
}