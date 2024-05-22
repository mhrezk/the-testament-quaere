package com.testament.veltahleon.repositories.calendar;

import com.testament.veltahleon.model.entities.calendar.CalendarSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarSystemRepository extends JpaRepository<CalendarSystem, Long> {
    CalendarSystem findByName(String name);
    long countByName(String name);
}
