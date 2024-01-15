package com.testament.veltahleon.services.dao.implementation.society;

import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.repositories.dao.ifc.society.PersonDAO;
import com.testament.veltahleon.services.dao.ifc.society.PersonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;
    
    @Override
    public Collection<Person> getPersons() {
        return personDAO.getPersons();
    }

    @Override
    public Person getPersonByID(Long id) {
        return personDAO.getPersonByID(id);
    }

    @Override
    @Transactional
    public Boolean deletePersonByID(Long id) {
        return personDAO.deletePersonByID(id);
    }

    @Override
    @Transactional
    public Person savePerson(Person person) {
        return personDAO.savePerson(person);
    }

    @Override
    @Transactional
    public Person updatePerson(Person person) {
        return personDAO.updatePerson(person);
    }
}
