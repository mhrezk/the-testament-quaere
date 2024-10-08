package com.testament.veltahleon.repositories.history;

import com.testament.veltahleon.model.entities.history.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    Timeline findByName(String name);
    List<Timeline> findByBeginningYearAndEndingYear(int beginningYear, int endingYear);
    List<Timeline> findByEndingYearAbbreviation(String endingYearAbbreviation);
    List<Timeline> findByBeginningYear(int beginningYear);
    List<Timeline> findByEndingYear(int endingYear);
//    Timeline findByEvents_Incident(String incident);
}
