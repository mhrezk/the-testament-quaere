package com.testament.veltahleon.services.ifc.history;

import com.testament.veltahleon.model.entities.history.Timeline;

import java.util.Collection;

public interface TimelineService {
    Collection<Timeline> getTimelines();
    Timeline getTimelineById(Long id);
    Timeline getTimelineByName(String name);
    Timeline getTimelineByEventIncident(String eventIncident);
}
