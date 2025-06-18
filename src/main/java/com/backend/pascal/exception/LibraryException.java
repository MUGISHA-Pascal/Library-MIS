package com.backend.pascal.exception;

/**
 * Custom exception for library-related errors.
 */
public class LibraryException extends RuntimeException {
    
    public LibraryException(String message) {
        super(message);
    }
    
    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}