package com.backend.pascal.service.impl;

import com.backend.pascal.dto.BookResponseDTO;
import com.backend.pascal.dto.BorrowingTransactionRequestDTO;
import com.backend.pascal.dto.BorrowingTransactionResponseDTO;
import com.backend.pascal.exception.LibraryException;
import com.backend.pascal.model.entity.Book;
import com.backend.pascal.model.entity.BorrowingTransaction;
import com.backend.pascal.model.enums.AvailabilityStatus;
import com.backend.pascal.model.enums.BorrowingStatus;
import com.backend.pascal.repository.BookRepository;
import com.backend.pascal.repository.BorrowingTransactionRepository;
import com.backend.pascal.service.BookService;
import com.backend.pascal.service.BorrowingTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Implementation of the BorrowingTransactionService interface.
 */
@Service
public class BorrowingTransactionServiceImpl implements BorrowingTransactionService {
    
    private final BorrowingTransactionRepository borrowingTransactionRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;
    
    @Autowired
    public BorrowingTransactionServiceImpl(
            BorrowingTransactionRepository borrowingTransactionRepository,
            BookRepository bookRepository,
            BookService bookService) {
        this.borrowingTransactionRepository = borrowingTransactionRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }
    
    @Override
    @Transactional
    public BorrowingTransactionResponseDTO createBorrowingTransaction(BorrowingTransactionRequestDTO requestDTO) {
        // Find the book by ISBN
        Book book = bookRepository.findByIsbn(requestDTO.getIsbn())
                .orElseThrow(() -> new LibraryException("Book with ISBN " + requestDTO.getIsbn() + " not found"));
        
        // Check if the book is available
        if (book.getAvailabilityStatus() != AvailabilityStatus.AVAILABLE) {
            throw new LibraryException("Book with ISBN " + requestDTO.getIsbn() + " is not available for borrowing");
        }
        
        // Create a new borrowing transaction
        BorrowingTransaction transaction = new BorrowingTransaction();
        transaction.setBook(book);
        transaction.setBorrowerName(requestDTO.getBorrowerName());
        transaction.setBorrowDate(requestDTO.getBorrowDate());
        transaction.setStatus(BorrowingStatus.PENDING);
        
        // Update book availability status
        book.setAvailabilityStatus(AvailabilityStatus.BORROWED);
        bookRepository.save(book);
        
        // Save the transaction
        BorrowingTransaction savedTransaction = borrowingTransactionRepository.save(transaction);
        
        // Convert to response DTO and return
        return convertToResponseDTO(savedTransaction);
    }
    
    @Override
    @Transactional
    public BorrowingTransactionResponseDTO returnBook(Long transactionId) {
        // Find the transaction
        BorrowingTransaction transaction = borrowingTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new LibraryException("Borrowing transaction with ID " + transactionId + " not found"));
        
        // Check if the transaction is already returned
        if (transaction.getStatus() == BorrowingStatus.RETURNED) {
            throw new LibraryException("Book has already been returned");
        }
        
        // Update transaction status and return date
        transaction.setStatus(BorrowingStatus.RETURNED);
        transaction.setReturnDate(LocalDateTime.now());
        
        // Update book availability status
        Book book = transaction.getBook();
        book.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        bookRepository.save(book);
        
        // Save the updated transaction
        BorrowingTransaction updatedTransaction = borrowingTransactionRepository.save(transaction);
        
        // Convert to response DTO and return
        return convertToResponseDTO(updatedTransaction);
    }
    
    /**
     * Convert a BorrowingTransaction entity to a BorrowingTransactionResponseDTO.
     *
     * @param transaction the borrowing transaction entity
     * @return the borrowing transaction response DTO
     */
    private BorrowingTransactionResponseDTO convertToResponseDTO(BorrowingTransaction transaction) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO(
                transaction.getBook().getId(),
                transaction.getBook().getTitle(),
                transaction.getBook().getAuthor(),
                transaction.getBook().getIsbn(),
                transaction.getBook().getAvailabilityStatus()
        );
        
        return new BorrowingTransactionResponseDTO(
                transaction.getId(),
                bookResponseDTO,
                transaction.getBorrowerName(),
                transaction.getBorrowDate(),
                transaction.getReturnDate(),
                transaction.getStatus()
        );
    }
}