package com.testament.veltahleon.services.entities.repo.implementation.society;


import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.society.PersonRepository;
import com.testament.veltahleon.services.entities.repo.ifc.society.PersonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Collection<Person> getPersonsWithPagination(int pageNumber, int numberOfRecords) {
        return personRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonByID(Long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @Override
    public Person getPersonByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public Boolean deletePersonByID(Long id) {
        personRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Long id, Person person) {
        Person newPerson = personRepository.findById(id).orElseThrow();

        if(person.getName() != null && newPerson.getName() != person.getName()) {
            newPerson.setName(person.getName());
        }

        if(person.getNation() != null && newPerson.getNation() != person.getNation()) {
            newPerson.setNation(person.getNation());
        }

        if(person.getReligion() != null && newPerson.getReligion() != person.getReligion()) {
            newPerson.setReligion(person.getReligion());
        }

        if(person.getRace() != null && newPerson.getRace() != person.getRace()) {
            newPerson.setRace(person.getRace());
        }

        if(person.getGender() != null && newPerson.getGender() != person.getGender()) {
            newPerson.setGender(person.getGender());
        }

        if(person.getFamily() != null && newPerson.getFamily() != person.getFamily()) {
            newPerson.setFamily(person.getFamily());
        }

        if(person.getJob() != null && newPerson.getJob() != person.getJob()) {
            newPerson.setJob(person.getJob());
        }

        if(person.getYearBirthAndDeath() != null && newPerson.getYearBirthAndDeath() != person.getYearBirthAndDeath()) {
            newPerson.setYearBirthAndDeath(person.getYearBirthAndDeath());
        }

        if(person.getImageURL() != null && newPerson.getImageURL() != person.getImageURL()) {
            newPerson.setImageURL(person.getImageURL());
        }

        if(person.getBiography() != null && newPerson.getBiography() != person.getBiography()) {
            newPerson.setBiography(person.getBiography());
        }

        return personRepository.save(newPerson);
    }
}
