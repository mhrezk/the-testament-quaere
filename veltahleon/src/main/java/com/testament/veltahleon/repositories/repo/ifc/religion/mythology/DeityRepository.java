package com.testament.veltahleon.repositories.repo.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.religion.mythology.Deity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface DeityRepository extends JpaRepository<Deity, Long> {}
