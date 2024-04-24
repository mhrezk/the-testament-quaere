package com.testament.veltahleon.repositories.calendar;

import com.testament.veltahleon.model.entities.calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Calendar findByName(String name);
    long countByName(String name);
}
