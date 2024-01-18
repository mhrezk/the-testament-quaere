package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.religion.mythology.Demon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface DemonRepository extends JpaRepository<Demon, Long> {}
