package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history;

import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.history.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language findByName(String name);
    long countByName(String name);
}
