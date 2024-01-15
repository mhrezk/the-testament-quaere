package com.testament.veltahleon.services.dao.ifc.society;

import com.testament.veltahleon.model.entities.society.Job;

import java.util.Collection;

public interface JobService {

    Collection<Job> getJobs();
    Job getJobByID(Long id);
    Boolean deleteJobByID(Long id);
    Job saveJob(Job fob);
    Job updateJob(Job fob);
}
