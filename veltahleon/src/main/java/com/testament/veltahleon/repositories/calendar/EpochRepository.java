package com.testament.veltahleon.repositories.calendar;

import com.testament.veltahleon.model.entities.calendar.Epoch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpochRepository extends JpaRepository<Epoch, Long> {

    Epoch findByYearName(String name);
    Epoch findByYearNumber(int yearNumber);
    long countByYearName(String name);
}
