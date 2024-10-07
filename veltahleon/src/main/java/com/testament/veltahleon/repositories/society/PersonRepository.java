package com.testament.veltahleon.repositories.society;

import com.testament.veltahleon.model.entities.society.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFirstName(String name);
    Person findByFirstNameAndSecondName(String firstName, String lastName);
    boolean existsPersonByFirstName(String name);
    long countByFirstName(String name);
}
