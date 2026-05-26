package com.library.exception;

/**
 * Exception thrown when a requested book
 * is currently unavailable.
 */
public class BookNotAvailableException extends Exception {

    public BookNotAvailableException(String message) {
        super(message);
    }
}