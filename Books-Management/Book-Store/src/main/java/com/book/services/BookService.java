package com.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entities.Book;
import com.book.entities.Transaction;
import com.book.entities.User;
import com.book.repositories.BookRepo;
import com.book.repositories.TransactionRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepository;

    @Autowired
    private TransactionRepo transactionRepository;

    // Method to retrieve all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Method to search for books by title, author, or category
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrCategoryContainingIgnoreCase(
                keyword, keyword, keyword);
    }

    // Method to borrow a book
    public Transaction borrowBook(Book book, User user) {
        if (book.isAvailable()) {
            book.setAvailable(false); // Mark the book as unavailable
            bookRepository.save(book);
            Transaction transaction = new Transaction();
            transaction.setBook(book);
            transaction.setUser(user);
            // Set the borrow date
            transaction.setBorrowDate(LocalDateTime.now());
            return transactionRepository.save(transaction);
        } else {
            throw new IllegalStateException("The book is not available for borrowing.");
        }
    }

    // Method to return a book
    public Transaction returnBook(Book book, User user) {
        Transaction transaction = transactionRepository.findByBookAndUser(book, user)
                .orElseThrow(() -> new IllegalStateException("No transaction found for this book and user."));
        if (transaction.getReturnDate() != null) {
            throw new IllegalStateException("The book has already been returned.");
        }
        // Set the return date
        transaction.setReturnDate(LocalDateTime.now());
        book.setAvailable(true); // Mark the book as available
        bookRepository.save(book);
        return transactionRepository.save(transaction);
    }

    // Method to add a new book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Method to update an existing book
    public Book updateBook(Long id, Book newBookData) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Book not found"));
        existingBook.setTitle(newBookData.getTitle());
        existingBook.setAuthor(newBookData.getAuthor());
        existingBook.setCategory(newBookData.getCategory());
        return bookRepository.save(existingBook);
    }

    // Method to delete a book by ID
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Method to retrieve a book by ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
}
