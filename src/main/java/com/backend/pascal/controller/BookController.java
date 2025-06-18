package com.backend.pascal.controller;

import com.backend.pascal.dto.BookRequestDTO;
import com.backend.pascal.dto.BookResponseDTO;
import com.backend.pascal.model.enums.AvailabilityStatus;
import com.backend.pascal.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing books.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {
    
    private final BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    /**
     * Create a new book.
     *
     * @param bookRequestDTO the book request DTO
     * @return the created book response DTO
     */
    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO createdBook = bookService.createBook(bookRequestDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }
    
    /**
     * Get a book by its ISBN.
     *
     * @param isbn the ISBN of the book
     * @return the book response DTO
     */
    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponseDTO> getBookByIsbn(@PathVariable String isbn) {
        BookResponseDTO book = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }
    
    /**
     * Get the availability status of a book by its ISBN.
     *
     * @param isbn the ISBN of the book
     * @return the availability status
     */
    @GetMapping("/{isbn}/availability")
    public ResponseEntity<String> getBookAvailability(@PathVariable String isbn) {
        AvailabilityStatus availabilityStatus = bookService.getBookAvailability(isbn);
        return ResponseEntity.ok(availabilityStatus.name());
    }
}