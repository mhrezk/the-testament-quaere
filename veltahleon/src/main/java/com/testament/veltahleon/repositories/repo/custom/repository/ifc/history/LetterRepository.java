package com.testament.veltahleon.repositories.repo.custom.repository.ifc.history;

import com.testament.veltahleon.model.entities.history.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {}
