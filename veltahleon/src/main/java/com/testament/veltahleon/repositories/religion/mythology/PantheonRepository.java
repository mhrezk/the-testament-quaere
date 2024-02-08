package com.testament.veltahleon.repositories.religion.mythology;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.religion.mythology.Pantheon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PantheonRepository extends JpaRepository<Pantheon, Long> {}
