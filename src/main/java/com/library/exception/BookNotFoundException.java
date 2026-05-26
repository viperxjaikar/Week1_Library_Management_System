package com.library.exception;

/**
 * Exception thrown when
 * a book cannot be found.
 */
public class BookNotFoundException extends Exception {

    public BookNotFoundException(String message) {
        super(message);
    }
}