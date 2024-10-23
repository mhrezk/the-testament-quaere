package com.testament.veltahleon.repositories.history;

import com.testament.veltahleon.model.entities.history.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {
    Collection<Letter> findByLanguage_Name(String languageName);
    Page<Letter> findByLanguage_Name(String languageName, Pageable pageable);
    Letter findByName(String letter);
    long countByLanguage_Name(String name);
}
