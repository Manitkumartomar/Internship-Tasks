package com.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.book.entities.Book;
import com.book.entities.Transaction;
import com.book.entities.User;
import com.book.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    // Endpoint to retrieve all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Endpoint to search for books by title, author, or category
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String keyword) {
        return bookService.searchBooks(keyword);
    }

    // Endpoint to borrow a book
    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<Transaction> borrowBook(@PathVariable Long bookId, @RequestBody User user) {
        Book book = bookService.getBookById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Transaction transaction = bookService.borrowBook(book, user);
        return ResponseEntity.ok(transaction);
    }

    // Endpoint to return a book
    @PostMapping("/{bookId}/return")
    public ResponseEntity<Transaction> returnBook(@PathVariable Long bookId, @RequestBody User user) {
        Book book = bookService.getBookById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Transaction transaction = bookService.returnBook(book, user);
        return ResponseEntity.ok(transaction);
    }

    // Endpoint to add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    // Endpoint to update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book newBookData) {
        Book updatedBook = bookService.updateBook(id, newBookData);
        return ResponseEntity.ok(updatedBook);
    }

    // Endpoint to delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
