package com.testament.veltahleon.repositories.repo.ifc.politics.military;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.politics.military.SquadLeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SquadLeaderDAO extends JpaRepository<SquadLeader, Long> {}
