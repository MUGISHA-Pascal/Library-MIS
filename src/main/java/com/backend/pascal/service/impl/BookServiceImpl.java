package com.backend.pascal.service.impl;

import com.backend.pascal.dto.BookRequestDTO;
import com.backend.pascal.dto.BookResponseDTO;
import com.backend.pascal.exception.LibraryException;
import com.backend.pascal.model.entity.Book;
import com.backend.pascal.model.enums.AvailabilityStatus;
import com.backend.pascal.repository.BookRepository;
import com.backend.pascal.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the BookService interface.
 */
@Service
public class BookServiceImpl implements BookService {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Override
    @Transactional
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        // Check if a book with the same ISBN already exists
        if (bookRepository.existsByIsbn(bookRequestDTO.getIsbn())) {
            throw new LibraryException("Book with ISBN " + bookRequestDTO.getIsbn() + " already exists");
        }
        
        // Create a new book entity
        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setAvailabilityStatus(bookRequestDTO.getAvailabilityStatus());
        
        // Save the book
        Book savedBook = bookRepository.save(book);
        
        // Convert to response DTO and return
        return convertToResponseDTO(savedBook);
    }
    
    @Override
    @Transactional(readOnly = true)
    public BookResponseDTO getBookByIsbn(String isbn) {
        Book book = findBookByIsbn(isbn);
        return convertToResponseDTO(book);
    }
    
    @Override
    @Transactional(readOnly = true)
    public AvailabilityStatus getBookAvailability(String isbn) {
        Book book = findBookByIsbn(isbn);
        return book.getAvailabilityStatus();
    }
    
    @Override
    @Transactional
    public BookResponseDTO updateBookAvailability(String isbn, AvailabilityStatus availabilityStatus) {
        Book book = findBookByIsbn(isbn);
        book.setAvailabilityStatus(availabilityStatus);
        Book updatedBook = bookRepository.save(book);
        return convertToResponseDTO(updatedBook);
    }
    
    /**
     * Find a book by its ISBN.
     *
     * @param isbn the ISBN of the book
     * @return the book entity
     * @throws LibraryException if the book is not found
     */
    private Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new LibraryException("Book with ISBN " + isbn + " not found"));
    }
    
    /**
     * Convert a Book entity to a BookResponseDTO.
     *
     * @param book the book entity
     * @return the book response DTO
     */
    private BookResponseDTO convertToResponseDTO(Book book) {
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getAvailabilityStatus()
        );
    }
}