package com.testament.veltahleon.repositories.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battalion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BattalionRepository extends JpaRepository<Battalion, Long> {

    Battalion findByName(String name);
    Battalion findByLeader_FirstName(String name);
}
