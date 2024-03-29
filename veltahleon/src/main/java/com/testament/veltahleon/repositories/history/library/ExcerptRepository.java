package com.testament.veltahleon.repositories.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.history.library.Excerpt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ExcerptRepository extends JpaRepository<Excerpt, Long> {

    Collection<Excerpt> findByBook_name(String name);
}
