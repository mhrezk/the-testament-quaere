package com.testament.veltahleon.services.dao.ifc.society;

import com.testament.veltahleon.model.entities.society.Person;

import java.util.Collection;

public interface PersonService {

    Collection<Person> getPersons();
    Person getPersonByID(Long id);
    Boolean deletePersonByID(Long id);
    Person savePerson(Person person);
    Person updatePerson(Person person);
}
