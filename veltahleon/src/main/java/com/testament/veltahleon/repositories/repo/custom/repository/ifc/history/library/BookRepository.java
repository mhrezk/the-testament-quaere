package com.testament.veltahleon.repositories.repo.custom.repository.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
