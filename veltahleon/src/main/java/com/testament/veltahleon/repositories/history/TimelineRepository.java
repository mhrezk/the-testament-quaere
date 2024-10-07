package com.testament.veltahleon.repositories.history;

import com.testament.veltahleon.model.entities.history.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    Timeline findByName(String name);
    Timeline findByEvents_Incident(String incident);
}
