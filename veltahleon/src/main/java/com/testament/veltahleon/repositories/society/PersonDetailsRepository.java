package com.testament.veltahleon.repositories.society;

import com.testament.veltahleon.model.entities.society.PersonDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDetailsRepository extends JpaRepository<PersonDetails, Long> {

    PersonDetails findByPerson_FirstNameAndPerson_SecondName(String firstName, String secondName);
}
