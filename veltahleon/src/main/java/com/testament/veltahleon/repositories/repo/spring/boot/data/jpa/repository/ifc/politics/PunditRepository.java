package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.politics;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.politics.Pundit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PunditRepository extends JpaRepository<Pundit, Long> {}
