package com.backend.pascal.service;

import com.backend.pascal.dto.BookRequestDTO;
import com.backend.pascal.dto.BookResponseDTO;
import com.backend.pascal.model.enums.AvailabilityStatus;

/**
 * Service interface for managing books.
 */
public interface BookService {
    
    /**
     * Create a new book.
     *
     * @param bookRequestDTO the book request DTO
     * @return the created book response DTO
     */
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    
    /**
     * Get a book by its ISBN.
     *
     * @param isbn the ISBN of the book
     * @return the book response DTO
     */
    BookResponseDTO getBookByIsbn(String isbn);
    
    /**
     * Get the availability status of a book by its ISBN.
     *
     * @param isbn the ISBN of the book
     * @return the availability status
     */
    AvailabilityStatus getBookAvailability(String isbn);
    
    /**
     * Update the availability status of a book.
     *
     * @param isbn the ISBN of the book
     * @param availabilityStatus the new availability status
     * @return the updated book response DTO
     */
    BookResponseDTO updateBookAvailability(String isbn, AvailabilityStatus availabilityStatus);
}