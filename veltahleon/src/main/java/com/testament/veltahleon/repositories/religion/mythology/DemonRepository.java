package com.testament.veltahleon.repositories.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Demon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemonRepository extends JpaRepository<Demon, Long> {

    Demon findByName(String name);
}