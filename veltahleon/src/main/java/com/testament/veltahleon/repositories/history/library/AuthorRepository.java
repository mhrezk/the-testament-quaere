package com.testament.veltahleon.repositories.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);
    Author findByBooks_Name(String name);
}
