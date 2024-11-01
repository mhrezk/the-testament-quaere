package com.testament.veltahleon.repositories.history;

import com.testament.veltahleon.model.entities.history.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByTimeline_Name(String timelineName, Pageable pageable);
    Event findByIncident(String incidentName);
    Event findByEventDayAndEventMonthAndEventYear(int day, int month, int year);
    List<Event> findByEventYear(int year);
    List<Event> findByTimeline_Name(String timelineName);
//    List<Event> findByBeginningYear_Epoch_YearNumber(int year);
//    List<Event> findByBeginningYear_Month_MonthNumber(int month);
//    List<Event> findByBeginningYear_Month_Name(String name);
//    List<Event> findByBeginningYear_Day_Name(String name);
//    List<Event> findByBeginningYear_Day_DayNumber(int day);
//    List<Event> findByEndingYear_Epoch_YearNumber(int year);
//    List<Event> findByEndingYear_Month_MonthNumber(int month);
//    List<Event> findByEndingYear_Month_Name(String name);
//    List<Event> findByEndingYear_Day_Name(String name);
//    List<Event> findByEndingYear_Day_DayNumber(int day);
}
