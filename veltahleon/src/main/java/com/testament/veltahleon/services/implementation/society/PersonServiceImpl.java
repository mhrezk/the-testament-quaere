package com.testament.veltahleon.services.implementation.society;


import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.repositories.society.PersonRepository;
import com.testament.veltahleon.services.ifc.society.PersonService;
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
            newPerson.setRace(checkRaceForUpdate(person.getRace(), newPerson.getRace()));
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

        if(person.getTitle() != null && newPerson.getTitle() != person.getTitle()) {
            newPerson.setTitle(person.getTitle());
        }

        return personRepository.save(newPerson);
    }

    @Override
    public Person modifyPerson(Long id, Person person) {
        Person newPerson = personRepository.findById(id).orElseThrow();
        newPerson.setName(person.getName());
        newPerson.setGender(person.getGender());
        newPerson.setBiography(person.getBiography());
        newPerson.setImageURL(person.getImageURL());
        newPerson.setRace(person.getRace());
        return personRepository.save(newPerson);
    }

    //Helper Methods
    private Race checkRaceForUpdate(Race race, Race newRace) {
        if(race.getName() != null && newRace.getName() != race.getName()) {
            newRace.setName(race.getName());
        }

        if(race.getDescription() != null && newRace.getDescription() != race.getDescription()) {
            newRace.setDescription(race.getDescription());
        }

        if(race.getImageURL() != null && newRace.getImageURL() != race.getImageURL()) {
            newRace.setImageURL(race.getImageURL());
        }

        return newRace;
    }
}
