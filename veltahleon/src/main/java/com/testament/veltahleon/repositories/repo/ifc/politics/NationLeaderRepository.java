package com.testament.veltahleon.repositories.repo.ifc.politics;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.politics.NationLeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface NationLeaderRepository extends JpaRepository<NationLeader, Long> {}
