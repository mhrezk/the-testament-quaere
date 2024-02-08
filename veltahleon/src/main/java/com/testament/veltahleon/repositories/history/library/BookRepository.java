package com.testament.veltahleon.repositories.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.history.library.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByName(String name);
    Book findByAuthor_Name(String name);
}
