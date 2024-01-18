package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.places;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.places.Capital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CapitalRepository extends JpaRepository<Capital, Long> {}