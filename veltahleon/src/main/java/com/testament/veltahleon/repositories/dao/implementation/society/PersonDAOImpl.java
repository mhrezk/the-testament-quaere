package com.testament.veltahleon.repositories.dao.implementation.society;

import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.repositories.dao.ifc.society.PersonDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Person> getPersons() {
        TypedQuery<Person> query = entityManager.createQuery("FROM Person", Person.class);
        return query.getResultList();
    }

    @Override
    public Person getPersonByID(Long id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public Boolean deletePersonByID(Long id) {
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);
        return Boolean.TRUE;
    }

    @Override
    public Person savePerson(Person person) {
        entityManager.persist(person);
        return person;
    }

    @Override
    public Person updatePerson(Person person) {
        return entityManager.merge(person);
    }
}
