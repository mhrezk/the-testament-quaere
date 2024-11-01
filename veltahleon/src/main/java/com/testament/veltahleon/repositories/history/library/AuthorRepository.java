package com.testament.veltahleon.repositories.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Collection<Author> findByFirstName(String name);
    Collection<Author> findByLastName(String name);
    Author findByUniqueValue(String uniqueID);
    Author findByFirstNameAndMiddleNameAndLastNameAndBirthYear(String firstName, String middleName, String lastName, int birthYear);
    Author findByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);
    Author findByFirstNameAndLastName(String firstName, String lastName);

    Author findByFirstNameAndLastNameAndUniqueValue(String firstName, String lastName, String uniqueID);
    Author findByIdAndFirstNameAndLastName(Long id, String firstName, String lastName);
    boolean existsByUniqueValue(String uniqueID);
    long countByIdAndFirstNameAndLastName(Long id, String firstName, String lastName);
    long countByUniqueValue(String uniqueID);
    long countByFirstNameAndLastName(String firstName, String lastName);
    //Author findByBooks_Name(String name);
}
