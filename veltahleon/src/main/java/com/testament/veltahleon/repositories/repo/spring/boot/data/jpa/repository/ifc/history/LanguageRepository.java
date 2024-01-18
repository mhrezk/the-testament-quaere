package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history;

import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.history.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface LanguageRepository extends JpaRepository<Language, Long> {}
