package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.society;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.society.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PersonRepository extends JpaRepository<Person, Long> {}
