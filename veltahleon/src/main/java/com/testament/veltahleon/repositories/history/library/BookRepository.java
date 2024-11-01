package com.testament.veltahleon.repositories.history.library;

import com.testament.veltahleon.model.entities.history.library.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByAuthor_IdAndAuthor_FirstNameAndAuthor_LastName(Long id, String firstName, String lastName, Pageable pageable);
    Collection<Book> findByAuthor_FirstNameAndAuthor_LastNameAndAuthor_Id(String firstName, String lastName, Long id);
    Collection<Book> findByAuthor_FirstName(String name);
    Collection<Book> findByAuthor_LastName(String name);
    Book findByName(String name);
    boolean existsByName(String name);
    long countByAuthor_IdAndAuthor_FirstNameAndAuthor_LastName(Long id, String firstName, String lastName);
    long countByName(String name);
}
