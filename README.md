# 📚 Library Management System

A console-based **Library Management System** built in Java as part of the **Week 1 — OOP & Collections** assignment. This project demonstrates core Object-Oriented Programming principles, Java Collections Framework, custom exception handling, and Java 8 features.

---

## 📌 About

This application simulates a real-world library where an admin can manage books, register users, and handle book borrowing/returning — all through an interactive console menu. It is designed to showcase clean coding practices with proper project structure, documentation, and error handling.

---

## 🚀 Features

| Feature | Description |
|---|---|
| Add / Remove Books | Manage the library's book catalog |
| Register Users | Add new members to the system |
| Borrow / Return Books | Lend and collect books with availability tracking |
| Search Books | Search by title (partial match), author, or genre |
| Available Books | View only books currently in stock |
| Genre Statistics | Count of books grouped by genre using Streams |
| Sorted Listing | View all books sorted alphabetically by title |
| Input Validation | Handles empty inputs and invalid menu choices gracefully |

---

## 🧠 Concepts Demonstrated

### 1. OOP Principles
- **Encapsulation** — Private fields with public getters/setters in `Book` and `User` classes
- **Abstraction** — `LibraryService` abstracts internal data structures behind clean public methods
- **Modularity** — Separate packages for models, services, and exceptions

### 2. Java Collections Framework
- **ArrayList** — Stores and manages the list of books (`List<Book>`)
- **HashMap** — Maps user IDs to User objects for fast lookup (`Map<String, User>`)

### 3. Exception Handling
- `BookNotAvailableException` — Thrown when trying to borrow a book that is already lent out
- `BookNotFoundException` — Thrown when a given book ID does not exist
- `UserNotFoundException` — Thrown when a given user ID does not exist
- Multi-catch blocks (`catch (A | B | C e)`) used in the main application

### 4. Java 8 Features
- **Lambda Expressions** — Used in `filter()`, `removeIf()`, `forEach()`, `sorted()`
- **Streams API** — Used for searching, filtering, sorting, and grouping operations
- **Method References** — `Book::isAvailable`, `Book::getTitle`, `System.out::println`
- **Collectors** — `Collectors.toList()`, `Collectors.groupingBy()`, `Collectors.counting()`

---

## 📁 Project Structure

```
Week1/
├── README.md
└── src/
    └── main/
        └── java/
            └── com/
                └── library/
                    ├── LibraryApp.java                    # Main application with menu
                    ├── model/
                    │   ├── Book.java                      # Book entity
                    │   └── User.java                      # User entity
                    ├── service/
                    │   └── LibraryService.java            # Core business logic
                    └── exception/
                        ├── BookNotAvailableException.java
                        ├── BookNotFoundException.java
                        └── UserNotFoundException.java
```

---

## ⚙️ How to Compile and Run

### Prerequisites
- Java JDK 8 or higher installed
- Terminal / Command Prompt

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/<your-username>/library-management-system.git
cd library-management-system/Week1

# 2. Compile all Java files
javac -d out src/main/java/com/library/exception/*.java \
              src/main/java/com/library/model/*.java \
              src/main/java/com/library/service/*.java \
              src/main/java/com/library/LibraryApp.java

# 3. Run the application
java -cp out com.library.LibraryApp
```

---

## 🖥️ Sample Output

```
=================================
 LIBRARY MANAGEMENT SYSTEM
 OOP + COLLECTIONS
=================================

----- MENU -----
1. Add Book
2. Remove Book
3. Search Books
4. Display Books
5. Register User
6. Display Users
7. Borrow Book
8. Return Book
9. Available Books
10. Genre Statistics
11. Sort Books
0. Exit

Enter choice: 1
Book ID: B001
Title: Clean Code
Author: Robert C. Martin
Genre: Programming
Book added successfully: Clean Code
```

---

## 🛠️ Technologies Used

- **Language:** Java 8+
- **Collections:** ArrayList, HashMap
- **Build:** Manual compilation (javac)
- **Version Control:** Git & GitHub

---

## 👤 Author

**Gonuguntala Jaikar Ramu**

---