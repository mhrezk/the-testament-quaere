package com.testament.veltahleon.services.implementation.history;

import com.testament.veltahleon.model.entities.history.Timeline;
import com.testament.veltahleon.repositories.history.TimelineRepository;
import com.testament.veltahleon.services.ifc.history.TimelineService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TimelineServiceImpl implements TimelineService {

    @Autowired
    private TimelineRepository timelineRepository;

    @Override
    public Collection<Timeline> getTimelines() {
        return timelineRepository.findAll();
    }

    @Override
    public Timeline getTimelineById(Long id) {
        return timelineRepository.findById(id).orElseThrow();
    }

    @Override
    public Timeline getTimelineByName(String name) {
        return timelineRepository.findByName(name);
    }

    @Override
    public Timeline getTimelineByEventIncident(String eventIncident) {
        return timelineRepository.findByEvents_Incident(eventIncident);
    }
}
