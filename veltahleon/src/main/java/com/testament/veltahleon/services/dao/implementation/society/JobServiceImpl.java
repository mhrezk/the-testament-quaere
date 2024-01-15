package com.testament.veltahleon.services.dao.implementation.society;

import com.testament.veltahleon.model.entities.society.Job;
import com.testament.veltahleon.repositories.dao.ifc.society.JobDAO;
import com.testament.veltahleon.services.dao.ifc.society.JobService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDAO jobDAO;
    
    @Override
    public Collection<Job> getJobs() {
        return jobDAO.getJobs();
    }

    @Override
    public Job getJobByID(Long id) {
        return jobDAO.getJobByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteJobByID(Long id) {
        return jobDAO.deleteJobByID(id);
    }

    @Override
    @Transactional
    public Job saveJob(Job job) {
        return jobDAO.saveJob(job);
    }

    @Override
    @Transactional
    public Job updateJob(Job job) {
        return jobDAO.updateJob(job);
    }
}
