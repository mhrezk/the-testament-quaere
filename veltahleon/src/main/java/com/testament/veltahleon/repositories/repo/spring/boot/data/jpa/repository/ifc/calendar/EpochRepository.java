package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Epoch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpochRepository extends JpaRepository<Epoch, Long> {

    Epoch findByName(String name);
}
