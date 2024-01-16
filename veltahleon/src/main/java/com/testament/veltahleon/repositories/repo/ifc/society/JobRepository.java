package com.testament.veltahleon.repositories.repo.ifc.society;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.society.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface JobRepository extends JpaRepository<Job, Long> {}
