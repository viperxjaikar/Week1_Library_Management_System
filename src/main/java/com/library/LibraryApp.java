package com.library;

import com.library.exception.BookNotAvailableException;
import com.library.exception.BookNotFoundException;
import com.library.exception.UserNotFoundException;
import com.library.model.Book;
import com.library.model.User;
import com.library.service.LibraryService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class LibraryApp {

    private static final LibraryService libraryService = new LibraryService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" LIBRARY MANAGEMENT SYSTEM ");
        System.out.println(" OOP + COLLECTIONS ");
        System.out.println("=================================");

        while (true) {
            displayMenu();
            int choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1:  addBook();                          break;
                case 2:  removeBook();                       break;
                case 3:  searchBooks();                      break;
                case 4:  libraryService.displayAllBooks();   break;
                case 5:  registerUser();                     break;
                case 6:  libraryService.displayAllUsers();   break;
                case 7:  borrowBook();                       break;
                case 8:  returnBook();                       break;
                case 9:  displayAvailableBooks();            break;
                case 10: displayGenreCount();                break;
                case 11: displaySortedBooks();               break;
                case 0:
                    System.out.println("\nGoodbye.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n----- MENU -----");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Search Books");
        System.out.println("4. Display Books");
        System.out.println("5. Register User");
        System.out.println("6. Display Users");
        System.out.println("7. Borrow Book");
        System.out.println("8. Return Book");
        System.out.println("9. Available Books");
        System.out.println("10. Genre Statistics");
        System.out.println("11. Sort Books");
        System.out.println("0. Exit");
    }

    private static void addBook() {
        String id = getStringInput("Book ID: ");
        String title = getStringInput("Title: ");
        String author = getStringInput("Author: ");
        String genre = getStringInput("Genre: ");
        libraryService.addBook(new Book(id, title, author, genre));
    }

    private static void removeBook() {
        try {
            libraryService.removeBook(getStringInput("Book ID: "));
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void searchBooks() {
        System.out.println("\n1. Title");
        System.out.println("2. Author");
        System.out.println("3. Genre");
        int choice = getIntInput("Select: ");

        List<Book> result;

        switch (choice) {
            case 1:
                result = libraryService.searchBooksByTitle(getStringInput("Title: "));
                break;
            case 2:
                result = libraryService.searchBooksByAuthor(getStringInput("Author: "));
                break;
            case 3:
                result = libraryService.searchBooksByGenre(getStringInput("Genre: "));
                break;
            default:
                System.out.println("Invalid search option.");
                return;
        }

        if (result.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        result.forEach(System.out::println);
    }

    private static void registerUser() {
        String id = getStringInput("User ID: ");
        String name = getStringInput("Name: ");
        String email = getStringInput("Email: ");
        libraryService.registerUser(new User(id, name, email));
    }

    private static void borrowBook() {
        try {
            String userId = getStringInput("User ID: ");
            String bookId = getStringInput("Book ID: ");
            libraryService.borrowBook(userId, bookId);
        } catch (UserNotFoundException | BookNotFoundException | BookNotAvailableException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void returnBook() {
        try {
            String userId = getStringInput("User ID: ");
            String bookId = getStringInput("Book ID: ");
            libraryService.returnBook(userId, bookId);
        } catch (UserNotFoundException | BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayAvailableBooks() {
        List<Book> books = libraryService.getAvailableBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        books.forEach(System.out::println);
    }

    private static void displayGenreCount() {
        Map<String, Long> map = libraryService.countBooksByGenre();
        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    private static void displaySortedBooks() {
        libraryService.getBooksSortedByTitle().forEach(System.out::println);
    }

    private static String getStringInput(String msg) {
        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    private static int getIntInput(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Enter valid number.");
            }
        }
    }
}
