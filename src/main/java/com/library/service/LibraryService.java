package com.library.service;

import com.library.exception.BookNotAvailableException;
import com.library.exception.BookNotFoundException;
import com.library.exception.UserNotFoundException;
import com.library.model.Book;
import com.library.model.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Core service class for Library Management System.
 *
 * Demonstrates:
 * - ArrayList for storing books
 * - HashMap for mapping userId to User objects
 * - Java 8 Streams and Lambda Expressions for searching/filtering
 * - Exception handling for invalid operations
 */
public class LibraryService {

    private final List<Book> books;            // ArrayList to manage books
    private final Map<String, User> users;     // HashMap to manage users (userId -> User)

    public LibraryService() {
        this.books = new ArrayList<>();
        this.users = new HashMap<>();
    }

    // ======================== Book Operations ========================

    /**
     * Add a new book to the library.
     */
   public void addBook(Book book) {

    boolean exists = books.stream()
            .anyMatch(
                b -> b.getBookId()
                      .equalsIgnoreCase(book.getBookId())
            );

    if (exists) {
        System.out.println("Book ID already exists.");
        return;
    }

    books.add(book);

    System.out.println(
            "Book added successfully: "
                    + book.getTitle()
    );
}

    /**
     * Remove a book from the library by ID.
     * Uses Lambda expression with removeIf.
     */
    public void removeBook(String bookId) throws BookNotFoundException {
        boolean removed = books.removeIf(book -> book.getBookId().equals(bookId));
        if (!removed) {
            throw new BookNotFoundException("Book with ID '" + bookId + "' not found.");
        }
        System.out.println("Book removed successfully. ID: " + bookId);
    }

    /**
     * Find a book by its ID.
     * Uses Java 8 Stream with filter and findFirst.
     */
    public Book findBookById(String bookId) throws BookNotFoundException {
        return books.stream()
                .filter(book -> book.getBookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with ID '" + bookId + "' not found."));
    }

    /**
     * Search books by title (partial match, case-insensitive).
     * Uses Java 8 Streams and Lambda expressions.
     */
    public List<Book> searchBooksByTitle(String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Search books by author.
     * Uses Java 8 Streams.
     */
    public List<Book> searchBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    /**
     * Search books by genre.
     */
    public List<Book> searchBooksByGenre(String genre) {
        return books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    /**
     * Get all available books.
     * Uses Stream with filter and Lambda.
     */
    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)    // Method reference (Java 8)
                .collect(Collectors.toList());
    }

    /**
     * Get all books sorted by title.
     * Uses Stream with sorted and Comparator Lambda.
     */
    public List<Book> getBooksSortedByTitle() {
        return books.stream()
                .sorted(Comparator.comparing(Book::getTitle))   // Lambda-based comparator
                .collect(Collectors.toList());
    }

    /**
     * Count books by genre.
     * Uses Streams with groupingBy collector.
     */
    public Map<String, Long> countBooksByGenre() {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));
    }

    /**
     * Get all books in the library.
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    // ======================== User Operations ========================

    /**
     * Register a new user.
     * Uses HashMap put operation.
     */
    public void registerUser(User user) {
        if (users.containsKey(user.getUserId())) {
            System.out.println("User with ID '" + user.getUserId() + "' already exists.");
            return;
        }
        users.put(user.getUserId(), user);
        System.out.println("User registered successfully: " + user.getName());
    }

    /**
     * Remove a user from the system.
     */
    public void removeUser(String userId) throws UserNotFoundException {
        if (users.remove(userId) == null) {
            throw new UserNotFoundException("User with ID '" + userId + "' not found.");
        }
        System.out.println("User removed successfully. ID: " + userId);
    }

    /**
     * Find a user by ID.
     * Uses HashMap get operation.
     */
    public User findUserById(String userId) throws UserNotFoundException {
        User user = users.get(userId);
        if (user == null) {
            throw new UserNotFoundException("User with ID '" + userId + "' not found.");
        }
        return user;
    }

    /**
     * Get all registered users.
     * Uses HashMap values.
     */
    public Collection<User> getAllUsers() {
        return users.values();
    }

    // ======================== Borrow / Return Operations ========================

    /**
     * Borrow a book for a user.
     * Demonstrates exception handling for multiple invalid scenarios.
     */
    public void borrowBook(String userId, String bookId) throws UserNotFoundException,
            BookNotFoundException, BookNotAvailableException {

        User user = findUserById(userId);
        Book book = findBookById(bookId);

        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book '" + book.getTitle() + "' is currently not available.");
        }

        book.setAvailable(false);
        user.borrowBook(book);
        System.out.println(user.getName() + " borrowed: " + book.getTitle());
    }

    /**
     * Return a borrowed book.
     */
    public void returnBook(String userId, String bookId) throws UserNotFoundException,
            BookNotFoundException {

        User user = findUserById(userId);
        Book book = findBookById(bookId);

        if (book.isAvailable()) {
            System.out.println("This book was not borrowed.");
            return;
        }

        book.setAvailable(true);
        user.returnBook(book);
        System.out.println(user.getName() + " returned: " + book.getTitle());
    }

    // ======================== Display Helpers ========================

    /**
     * Display all books in a formatted table.
     */
    public void displayAllBooks() {
        System.out.println("\n==================== ALL BOOKS ====================");
        System.out.printf("| %-6s | %-30s | %-20s | %-12s | %-10s |%n",
                "ID", "Title", "Author", "Genre", "Available");
        System.out.println("|--------|--------------------------------|----------------------|--------------|------------|");
        books.forEach(System.out::println);     // Method reference (Java 8)
        System.out.println("====================================================\n");
    }

    /**
     * Display all users in a formatted table.
     */
    public void displayAllUsers() {
        System.out.println("\n==================== ALL USERS ====================");
        System.out.printf("| %-6s | %-20s | %-25s | %-15s |%n",
                "ID", "Name", "Email", "Books Borrowed");
        System.out.println("|--------|----------------------|---------------------------|-----------------|");
        users.values().forEach(System.out::println);    // forEach with method reference
        System.out.println("====================================================\n");
    }
}
