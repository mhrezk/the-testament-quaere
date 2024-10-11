package com.testament.veltahleon.services.ifc.history;

import com.testament.veltahleon.model.entities.history.Timeline;

import java.util.Collection;

public interface TimelineService {
    Collection<Timeline> getPaginatedTimelines(int pageNumber, int pageSize);
    Collection<Timeline> getTimelines();
    Collection<Timeline> getTimelinesByBeginningYear(int beginningYear);
    Collection<Timeline> getTimelinesByEndingYear(int endingYear);
    Collection<Timeline> getTimelinesByBeginningYearAndEndingYear(int beginningYear, int endingYear);
    Collection<Timeline> getTimelinesByEndingYearAbbreviation(String abbreviation);
    Timeline getTimelineById(Long id);
//    Timeline getTimelineByEventIDs(Long... eventIDs);
    Timeline getTimelineByName(String name);
    Timeline saveTimeline(Timeline timeline);
    Boolean deleteTimelineByID(Long id);
    Timeline updateTimeline(Long id, Timeline timeline);
    Timeline modifyTimeline(Long id, Timeline timeline);
    //Timeline getTimelineByEventIncident(String eventIncident);
    Long countTimelines();
}
