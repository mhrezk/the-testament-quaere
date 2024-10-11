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

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TimelineServiceImpl implements TimelineService {

    @Autowired
    private TimelineRepository timelineRepository;

    @Override
    public Collection<Timeline> getPaginatedTimelines(int pageNumber, int pageSize) {
        return timelineRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public Collection<Timeline> getTimelines() {
        return timelineRepository.findAll();
    }

    @Override
    public Collection<Timeline> getTimelinesByBeginningYear(int beginningYear) {
        return timelineRepository.findByBeginningYear(beginningYear);
    }

    @Override
    public Collection<Timeline> getTimelinesByEndingYear(int endingYear) {
        return timelineRepository.findByEndingYear(endingYear);
    }

    @Override
    public Collection<Timeline> getTimelinesByBeginningYearAndEndingYear(int beginningYear, int endingYear) {
        return timelineRepository.findByBeginningYearAndEndingYear(beginningYear, endingYear);
    }

    @Override
    public Collection<Timeline> getTimelinesByEndingYearAbbreviation(String abbreviation) {
        return timelineRepository.findByEndingYearAbbreviation(abbreviation);
    }

    @Override
    public Timeline getTimelineById(Long id) {
        return timelineRepository.findById(id).orElseThrow();
    }

//    @Override
//    public Timeline getTimelineByEventIDs(Long... eventIDs) {
//        return timelineRepository.findByEvents_Id(eventIDs);
//    }

    @Override
    public Timeline getTimelineByName(String name) {
        return timelineRepository.findByName(name);
    }

    @Override
    public Timeline saveTimeline(Timeline timeline) {
        return timelineRepository.save(timeline);
    }

    @Override
    public Boolean deleteTimelineByID(Long id) {
        timelineRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Timeline updateTimeline(Long id, Timeline timeline) {
        Timeline newTimeline = timelineRepository.findById(id).orElseThrow();

        if(timeline.getName() != null && newTimeline.getName() != timeline.getName()) {
            newTimeline.setName(timeline.getName());
        }

        if(timeline.getBeginningYear() != null && newTimeline.getBeginningYear() != timeline.getBeginningYear()) {
            newTimeline.setBeginningYear(timeline.getBeginningYear());
        }

        if(timeline.getEndingYear() != null && newTimeline.getEndingYear() != timeline.getEndingYear()) {
            newTimeline.setEndingYear(timeline.getEndingYear());
        }

        if(timeline.getBeginningYearAbbreviation() != null && newTimeline.getBeginningYearAbbreviation() != timeline.getBeginningYearAbbreviation()) {
            newTimeline.setBeginningYearAbbreviation(timeline.getBeginningYearAbbreviation());
        }

        if(timeline.getEndingYearAbbreviation() != null && newTimeline.getEndingYearAbbreviation() != timeline.getEndingYearAbbreviation()) {
            newTimeline.setEndingYearAbbreviation(timeline.getEndingYearAbbreviation());
        }

        return timelineRepository.save(newTimeline);
    }

    @Override
    public Timeline modifyTimeline(Long id, Timeline timeline) {
        Timeline newTimeline = timelineRepository.findById(id).orElseThrow();
        newTimeline.setName(timeline.getName());
        newTimeline.setBeginningYear(timeline.getBeginningYear());
        newTimeline.setEndingYear(timeline.getEndingYear());
        newTimeline.setBeginningYearAbbreviation(timeline.getBeginningYearAbbreviation());
        newTimeline.setEndingYearAbbreviation(timeline.getEndingYearAbbreviation());
        return timelineRepository.save(newTimeline);
    }

    @Override
    public Long countTimelines() {
        return timelineRepository.count();
    }

//    @Override
//    public Timeline getTimelineByEventIncident(String eventIncident) {
//        return timelineRepository.findByEvents_Incident(eventIncident);
//    }
}
