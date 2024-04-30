package com.testament.veltahleon.repositories.history;

import com.testament.veltahleon.model.entities.history.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {
    Collection<Letter> findByLanguage_Name(String languageName);
    Letter findByName(String letter);
    long countByLanguage_Name(String name);
}
