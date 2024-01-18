package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.religion;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.religion.Prophet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProphetRepository extends JpaRepository<Prophet, Long> {}
