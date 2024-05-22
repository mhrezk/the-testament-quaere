package com.testament.veltahleon.repositories.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SquadRepository extends JpaRepository<Squad, Long> {

    Squad findByLeader_FirstName(String name);
}
