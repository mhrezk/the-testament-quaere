package com.testament.veltahleon.repositories.dao.implementation.society;

import com.testament.veltahleon.model.entities.society.Job;
import com.testament.veltahleon.repositories.dao.ifc.society.JobDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class JobDAOImpl implements JobDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Job> getJobs() {
        TypedQuery<Job> query = entityManager.createQuery("FROM Job", Job.class);
        return query.getResultList();
    }

    @Override
    public Job getJobByID(Long id) {
        return entityManager.find(Job.class, id);
    }

    @Override
    public Boolean deleteJobByID(Long id) {
        Job job = entityManager.find(Job.class, id);
        entityManager.remove(job);
        return Boolean.TRUE;
    }

    @Override
    public Job saveJob(Job job) {
        entityManager.persist(job);
        return job;
    }

    @Override
    public Job updateJob(Job job) {
        return entityManager.merge(job);
    }
}
