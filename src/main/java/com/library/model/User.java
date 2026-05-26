package com.library.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a User/Member of the Library.
 */
public class User {

    private String userId;
    private String name;
    private String email;
    private List<Book> borrowedBooks;

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return String.format(
                "| %-6s | %-20s | %-25s | %-15d |",
                userId,
                name,
                email,
                borrowedBooks.size()
        );
    }
}