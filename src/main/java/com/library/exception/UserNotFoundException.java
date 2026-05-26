package com.library.exception;

/**
 * Exception thrown when
 * a user cannot be found.
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}