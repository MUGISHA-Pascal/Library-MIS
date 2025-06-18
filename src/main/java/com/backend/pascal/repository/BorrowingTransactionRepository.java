package com.backend.pascal.repository;

import com.backend.pascal.model.entity.Book;
import com.backend.pascal.model.entity.BorrowingTransaction;
import com.backend.pascal.model.enums.BorrowingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for BorrowingTransaction entity.
 */
@Repository
public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Long> {
    
    /**
     * Find all borrowing transactions for a specific book.
     * 
     * @param book the book to find transactions for
     * @return a list of borrowing transactions for the book
     */
    List<BorrowingTransaction> findByBook(Book book);
    
    /**
     * Find the latest active (PENDING) borrowing transaction for a book.
     * 
     * @param book the book to find the active transaction for
     * @param status the status of the transaction (PENDING)
     * @return an Optional containing the active transaction if found, or empty if not found
     */
    Optional<BorrowingTransaction> findFirstByBookAndStatusOrderByBorrowDateDesc(Book book, BorrowingStatus status);
    
    /**
     * Find all borrowing transactions for a specific borrower.
     * 
     * @param borrowerName the name of the borrower
     * @return a list of borrowing transactions for the borrower
     */
    List<BorrowingTransaction> findByBorrowerName(String borrowerName);
}