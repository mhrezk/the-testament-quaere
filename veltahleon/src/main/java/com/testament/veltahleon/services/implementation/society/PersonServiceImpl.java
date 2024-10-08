package com.testament.veltahleon.services.implementation.society;


import com.testament.veltahleon.enumerations.Lineage;
import com.testament.veltahleon.facades.society.PersonDetailsFacade;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.politics.Rank;
import com.testament.veltahleon.model.entities.society.Job;
import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.model.entities.society.PersonDetails;
import com.testament.veltahleon.repositories.society.PersonDetailsRepository;
import com.testament.veltahleon.repositories.society.PersonRepository;
import com.testament.veltahleon.services.ifc.society.PersonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonDetailsRepository personDetailsRepository;

    @Autowired
    private PersonDetailsFacade personDetailsFacade;

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
        return personRepository.findByFirstName(name);
    }

    @Override
    public Person getPersonByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findByFirstNameAndSecondName(firstName, lastName);
    }

    @Override
    public Boolean doesPersonNameExist(String name) {
        return personRepository.existsPersonByFirstName(name);
    }

    @Override
    public Person getPersonIfExistsByName(String name) {
        if(personRepository.existsPersonByFirstName(name)) {
            return personRepository.findByFirstName(name);
        }
        return null;
    }

    @Override
    public Boolean deletePersonByID(Long id) {
        personRepository.deleteById(id);
        personDetailsRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Person savePerson(Person person) {
        personRepository.save(person);
        PersonDetails newPersonDetails = generatePersonDetails();
        newPersonDetails.setPerson(person);
        personDetailsRepository.save(newPersonDetails);
        return person;
    }

    private PersonDetails generatePersonDetails() {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setYearAbbreviation(null);
        personDetails.setLineage(Lineage.NONE);
        personDetails.setBirthDay(0);
        personDetails.setBirthMonth(0);
        personDetails.setBirthYear(0);
        personDetails.setDeathDay(0);
        personDetails.setDeathMonth(0);
        personDetails.setDeathYear(0);
        personDetails.setRank(personDetailsFacade.createRank());
        personDetails.setReligion(personDetailsFacade.createReligion());
        personDetails.setJob(personDetailsFacade.createJob());
        personDetails.setNation(personDetailsFacade.createNation());
        personDetails.setImageURL(null);
        return personDetails;
    }

    @Override
    public Person updatePerson(Long id, Person person) {
        Person newPerson = personRepository.findById(id).orElseThrow();

        if(person.getFirstName() != null && newPerson.getFirstName() != person.getFirstName()) {
            newPerson.setFirstName(person.getFirstName());
        }

        if(person.getSecondName() != null && newPerson.getSecondName() != person.getSecondName()) {
            newPerson.setSecondName(person.getSecondName());
        }

//        if(person.getNation() != null && newPerson.getNation() != person.getNation()) {
//            newPerson.setNation(person.getNation());
//        }

//        if(person.getFamily() != null && newPerson.getFamily() != person.getFamily()) {
//            newPerson.setFamily(person.getFamily());
//        }

//        if(person.getReligion() != null && newPerson.getReligion() != person.getReligion()) {
//            newPerson.setReligion(person.getReligion());
//        }

        if(person.getRaceName() != null && newPerson.getRaceName() != person.getRaceName()) {
            newPerson.setRaceName(checkRaceForUpdate(person.getRaceName(), newPerson.getRaceName()));
        }

        if(person.getGender() != null && newPerson.getGender() != person.getGender()) {
            newPerson.setGender(person.getGender());
        }

//        if(person.getJob() != null && newPerson.getJob() != person.getJob()) {
//            newPerson.setJob(person.getJob());
//        }
//
//        if(person.getBirthYear() != null && newPerson.getBirthYear() != person.getBirthYear()) {
//            newPerson.setBirthYear(person.getBirthYear());
//        }
//
//        if(person.getDeathYear() != null && newPerson.getDeathYear() != person.getDeathYear()) {
//            newPerson.setDeathYear(person.getDeathYear());
//        }
//
//        if(person.getImageURL() != null && newPerson.getImageURL() != person.getImageURL()) {
//            newPerson.setImageURL(person.getImageURL());
//        }
//
//        if(person.getBiography() != null && newPerson.getBiography() != person.getBiography()) {
//            newPerson.setBiography(person.getBiography());
//        }
//
//        if(person.getTitle() != null && newPerson.getTitle() != person.getTitle()) {
//            newPerson.setTitle(person.getTitle());
//        }
//
//        if(person.getIsBastard() != null && newPerson.getIsBastard() != person.getIsBastard()) {
//            newPerson.setIsBastard(person.getIsBastard());
//        }

        return personRepository.save(newPerson);
    }

    @Override
    public Person modifyPerson(Long id, Person person) {
        Person newPerson = personRepository.findById(id).orElseThrow();
        newPerson.setFirstName(person.getFirstName());
        newPerson.setSecondName(person.getSecondName());
        newPerson.setGender(person.getGender());
        newPerson.setRaceName(person.getRaceName());
        PersonDetails newPersonDetails = personDetailsRepository.findById(id).orElseThrow();
        newPersonDetails.setPerson(newPerson);
        personRepository.save(newPerson);
        personDetailsRepository.save(newPersonDetails);
        return newPerson;
    }

    @Override
    public Long countPeople() {
        return personRepository.count();
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
