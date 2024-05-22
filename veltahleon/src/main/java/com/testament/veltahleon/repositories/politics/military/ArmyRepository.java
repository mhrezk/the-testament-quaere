package com.testament.veltahleon.repositories.politics.military;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.politics.military.Army;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ArmyRepository extends JpaRepository<Army, Long> {

    Army findByLeader_FirstName(String name);
}
