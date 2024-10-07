package com.testament.veltahleon.services.implementation.society;

import com.testament.veltahleon.enumerations.JobType;
import com.testament.veltahleon.model.entities.society.Job;
import com.testament.veltahleon.repositories.society.JobRepository;
import com.testament.veltahleon.services.ifc.society.JobService;
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
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Collection<Job> getJobsWithPagination(int pageNumber, int numberOfRecords) {
        return jobRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobByID(Long id) {
        return jobRepository.findById(id).orElseThrow();
    }

    @Override
    public Job getJobByName(String name) {
        if(jobRepository.countByName(name) <= 0) {
            Job newJob = new Job();
            String firstLetter = name.substring(0, 1).toUpperCase();
            String word = name.substring(1).toLowerCase();
            newJob.setName(firstLetter + word);
            return jobRepository.save(newJob);
        } else {
            return jobRepository.findByName(name);
        }
    }

    @Override
    public Boolean deleteJobByID(Long id) {
        jobRepository.findById(id);
        return Boolean.TRUE;
    }

    @Override
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Job newJob = jobRepository.findById(id).orElseThrow();

        if(job.getName() != null && newJob.getName() != job.getName()) {
            newJob.setName(job.getName());
        }

        if(job.getJobType() != null && newJob.getJobType() != job.getJobType()) {
            newJob.setJobType(job.getJobType());
        }

        return jobRepository.save(newJob);
    }
}
