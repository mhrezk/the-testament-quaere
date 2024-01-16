package com.testament.veltahleon.repositories.repo.ifc.politics.military;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.politics.military.Battle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BattleRepository extends JpaRepository<Battle, Long> {}
