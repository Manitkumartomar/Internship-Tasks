package com.book.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.entities.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    // Custom query method to search for books by title, author, or category
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrCategoryContainingIgnoreCase(String title,
            String author, String category);
}
