package com.testament.veltahleon.services.ifc.society;

import com.testament.veltahleon.model.entities.society.Job;

import java.util.Collection;

public interface JobService {

    Collection<Job> getJobsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Job> getJobs();
    Job getJobByID(Long id);
    Boolean deleteJobByID(Long id);
    Job saveJob(Job job);
    Job updateJob(Long id, Job job);
}
