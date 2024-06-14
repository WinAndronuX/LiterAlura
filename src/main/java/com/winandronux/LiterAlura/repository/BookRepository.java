package com.winandronux.LiterAlura.repository;

import com.winandronux.LiterAlura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    boolean existsByTitle(String title);
    @Query("SELECT b FROM Book b WHERE b.language LIKE :lang")
    List<Book> findAllByLanguage(String lang);
}
