package com.testament.veltahleon.repositories.repo.ifc.society;

import com.testament.veltahleon.model.entities.society.Job;

import java.util.Collection;

public interface JobRepository {

    Collection<Job> getJobs();
    Job getJobByID(Long id);
    Boolean deleteJobByID(Long id);
    Job saveJob(Job job);
    Job updateJob(Job job);
}
