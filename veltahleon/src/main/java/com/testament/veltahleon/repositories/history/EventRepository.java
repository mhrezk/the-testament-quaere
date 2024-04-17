package com.testament.veltahleon.repositories.history;

import com.testament.veltahleon.model.entities.history.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByYear_Epoch_YearNumber(int year);
    List<Event> findByYear_Month_MonthNumber(int month);
    List<Event> findByYear_Month_Name(String name);
    List<Event> findByYear_Day_Name(String name);
    List<Event> findByYear_Day_DayNumber(int day);
}
