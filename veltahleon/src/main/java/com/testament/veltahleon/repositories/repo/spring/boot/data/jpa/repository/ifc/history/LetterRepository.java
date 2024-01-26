package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history;

import com.testament.veltahleon.model.entities.history.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {
    Letter findByName(String name);
    //Collection<Letter> findByLanguageName(String languageName);
    long countByName(String name);
}
