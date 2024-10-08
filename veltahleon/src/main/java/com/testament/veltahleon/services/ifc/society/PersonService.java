package com.testament.veltahleon.services.ifc.society;

import com.testament.veltahleon.model.entities.society.Person;

import java.util.Collection;

public interface PersonService {

    Collection<Person> getPersonsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Person> getPersons();
    Person getPersonByID(Long id);
    Person getPersonByName(String name);
    Person getPersonByFirstNameAndLastName(String firstName, String lastName);
    Boolean doesPersonNameExist(String name);
    Person getPersonIfExistsByName(String name);
    Boolean deletePersonByID(Long id);
    Person savePerson(Person person);
    Person updatePerson(Long id, Person person);
    Person modifyPerson(Long id, Person person);
    Long countPeople();
}
