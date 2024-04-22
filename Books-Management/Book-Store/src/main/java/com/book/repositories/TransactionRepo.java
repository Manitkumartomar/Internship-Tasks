package com.book.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.entities.Book;
import com.book.entities.Transaction;
import com.book.entities.User;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    // Custom query method to find transactions by user
    List<Transaction> findByUser(User user);

    // Custom query method to find transaction by book and user
    Optional<Transaction> findByBookAndUser(Book book, User user);
}
