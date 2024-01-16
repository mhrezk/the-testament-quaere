package com.testament.veltahleon.repositories.repo.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.history.library.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {}
