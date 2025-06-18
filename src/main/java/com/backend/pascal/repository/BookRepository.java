package com.backend.pascal.repository;

import com.backend.pascal.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Book entity.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    /**
     * Find a book by its ISBN.
     * 
     * @param isbn the ISBN of the book
     * @return an Optional containing the book if found, or empty if not found
     */
    Optional<Book> findByIsbn(String isbn);
    
    /**
     * Check if a book with the given ISBN exists.
     * 
     * @param isbn the ISBN to check
     * @return true if a book with the given ISBN exists, false otherwise
     */
    boolean existsByIsbn(String isbn);
}