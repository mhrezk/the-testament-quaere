package com.testament.veltahleon.repositories.history;

import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.history.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language findByName(String name);
    boolean existsLanguageByName(String name);
    long countByName(String name);

    @Modifying
    @Query("update Language l set l.alphabetSize = :number where l.id = :id")
    void updateAlphabetSize(@Param("number") Integer number, @Param("id") Long id);
}
