package com.testament.veltahleon.repositories.society;

import com.testament.veltahleon.model.entities.society.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    Job findByName(String name);
}
