package com.testament.veltahleon.repositories.dao.ifc.society;

import com.testament.veltahleon.model.entities.society.Job;

import java.util.Collection;

public interface JobDAO {

    Collection<Job> getJobs();
    Job getJobByID(Long id);
    Boolean deleteJobByID(Long id);
    Job saveJob(Job job);
    Job updateJob(Job job);
}
