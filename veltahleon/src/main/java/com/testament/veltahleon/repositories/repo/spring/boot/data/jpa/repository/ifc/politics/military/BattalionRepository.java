package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.politics.military;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.politics.military.Battalion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BattalionRepository extends JpaRepository<Battalion, Long> {}
