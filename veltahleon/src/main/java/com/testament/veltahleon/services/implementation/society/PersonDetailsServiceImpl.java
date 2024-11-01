package com.testament.veltahleon.services.implementation.society;

import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.model.entities.society.PersonDetails;
import com.testament.veltahleon.repositories.society.PersonDetailsRepository;
import com.testament.veltahleon.repositories.society.PersonRepository;
import com.testament.veltahleon.services.ifc.society.PersonDetailsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PersonDetailsServiceImpl implements PersonDetailsService {

    @Autowired
    private PersonDetailsRepository personDetailsRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonDetails getPersonByFirstNameAndSecondName(String firstName, String secondName) {
        return personDetailsRepository.findByPerson_FirstNameAndPerson_SecondName(firstName, secondName);
    }

    @Override
    public Boolean deletePersonDetailsByID(Long id) {
        personRepository.deleteById(id);
        personDetailsRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public PersonDetails updatePersonDetails(Long id, PersonDetails personDetails) {
        PersonDetails newPersonDetails = personDetailsRepository.findById(id).orElseThrow();
        Person newPerson = personRepository.findById(id).orElseThrow();

        if(personDetails.getPerson().getFirstName() != null && newPerson.getFirstName() != personDetails.getPerson().getFirstName()) {
            newPerson.setFirstName(personDetails.getPerson().getFirstName().toUpperCase());
        }

        if(personDetails.getPerson().getSecondName() != null && newPerson.getSecondName() != personDetails.getPerson().getSecondName()) {
            newPerson.setSecondName(personDetails.getPerson().getSecondName().toUpperCase());
        }

        if(personDetails.getPerson().getGender() != null && newPerson.getGender() != personDetails.getPerson().getGender()) {
            newPerson.setGender(personDetails.getPerson().getGender());
        }

        if(personDetails.getPerson().getRaceName() != null && newPerson.getRaceName() != personDetails.getPerson().getRaceName()) {
            newPerson.setRaceName(personDetails.getPerson().getRaceName());
        }

        newPersonDetails.setPerson(newPerson);

        if(personDetails.getBiography() != null && newPersonDetails.getBiography() != personDetails.getBiography()) {
            newPersonDetails.setBiography(personDetails.getBiography());
        }

        if(personDetails.getBirthDay() != null && newPersonDetails.getBirthDay() != personDetails.getBirthDay()) {
            newPersonDetails.setBirthDay(personDetails.getBirthDay());
        }

        if(personDetails.getBirthMonth() != null && newPersonDetails.getBirthMonth() != personDetails.getBirthMonth()) {
            newPersonDetails.setBirthMonth(personDetails.getBirthMonth());
        }

        if(personDetails.getBirthYear() != null && newPersonDetails.getBirthYear() != personDetails.getBirthYear()) {
            newPersonDetails.setBirthYear(personDetails.getBirthYear());
        }

        if(personDetails.getDeathDay() != null && newPersonDetails.getDeathDay() != personDetails.getDeathDay()) {
            newPersonDetails.setDeathDay(personDetails.getDeathDay());
        }

        if(personDetails.getDeathMonth() != null && newPersonDetails.getDeathMonth() != personDetails.getDeathMonth()) {
            newPersonDetails.setDeathMonth(personDetails.getDeathMonth());
        }

        if(personDetails.getDeathYear() != null && newPersonDetails.getDeathYear() != personDetails.getDeathYear()) {
            newPersonDetails.setDeathYear(personDetails.getDeathYear());
        }

        if(personDetails.getJob() != null && newPersonDetails.getJob() != personDetails.getJob()) {
            newPersonDetails.setJob(personDetails.getJob());
        }

        if(personDetails.getEpithet() != null && newPersonDetails.getEpithet() != personDetails.getEpithet()) {
            newPersonDetails.setEpithet(personDetails.getEpithet());
        }

        if(personDetails.getLineage() != null && newPersonDetails.getLineage() != personDetails.getLineage()) {
            newPersonDetails.setLineage(personDetails.getLineage());
        }

        if(personDetails.getMaritalStatus() != null && newPersonDetails.getMaritalStatus() != personDetails.getMaritalStatus()) {
            newPersonDetails.setMaritalStatus(personDetails.getMaritalStatus());
        }

        if(personDetails.getRank() != null && newPersonDetails.getRank() != personDetails.getRank()) {
            newPersonDetails.setRank(personDetails.getRank());
        }

        if(personDetails.getReligion() != null && newPersonDetails.getReligion() != personDetails.getReligion()) {
            newPersonDetails.setReligion(personDetails.getReligion());
        }

        if(personDetails.getNation() != null && newPersonDetails.getNation() != personDetails.getNation()) {
            newPersonDetails.setNation(personDetails.getNation());
        }

        if(personDetails.getImageURL() != null && newPersonDetails.getImageURL() != personDetails.getImageURL()) {
            newPersonDetails.setImageURL(personDetails.getImageURL());
        }

        if(personDetails.getDeathYearAbbreviation() != null && newPersonDetails.getDeathYearAbbreviation() != personDetails.getDeathYearAbbreviation()) {
            newPersonDetails.setDeathYearAbbreviation(personDetails.getDeathYearAbbreviation());
        }

        if(personDetails.getBirthYearAbbreviation() != null && newPersonDetails.getBirthYearAbbreviation() != personDetails.getBirthYearAbbreviation()) {
            newPersonDetails.setBirthYearAbbreviation(personDetails.getBirthYearAbbreviation());
        }

        return personDetailsRepository.save(newPersonDetails);
    }


    @Override
    public PersonDetails modifyPersonDetails(Long id, PersonDetails personDetails) {
        PersonDetails newPersonDetails = personDetailsRepository.findById(id).orElseThrow();
        Person newPerson = personRepository.findById(id).orElseThrow();
        newPerson.setFirstName(personDetails.getPerson().getFirstName().toUpperCase());
        newPerson.setSecondName(personDetails.getPerson().getSecondName().toUpperCase());
        newPerson.setRaceName(personDetails.getPerson().getRaceName());
        newPerson.setGender(personDetails.getPerson().getGender());
        newPersonDetails.setPerson(newPerson);
        newPersonDetails.setNation(personDetails.getNation());
        newPersonDetails.setReligion(personDetails.getReligion());
        newPersonDetails.setRank(personDetails.getRank());
        newPersonDetails.setEpithet(personDetails.getEpithet());
        newPersonDetails.setJob(personDetails.getJob());
        newPersonDetails.setBirthDay(personDetails.getBirthDay());
        newPersonDetails.setBirthMonth(personDetails.getBirthMonth());
        newPersonDetails.setBirthYear(personDetails.getBirthYear());
        newPersonDetails.setBirthYearAbbreviation(personDetails.getBirthYearAbbreviation());
        newPersonDetails.setDeathDay(personDetails.getDeathDay());
        newPersonDetails.setDeathMonth(personDetails.getDeathMonth());
        newPersonDetails.setDeathYear(personDetails.getDeathYear());
        newPersonDetails.setDeathYearAbbreviation(personDetails.getDeathYearAbbreviation());
        //newPersonDetails.setCalendarSystem(personDetails.getCalendarSystem());
        newPersonDetails.setBiography(personDetails.getBiography());
        newPersonDetails.setLineage(personDetails.getLineage());
        newPersonDetails.setMaritalStatus(personDetails.getMaritalStatus());
        newPersonDetails.setImageURL(personDetails.getImageURL());
        return personDetailsRepository.save(newPersonDetails);
    }
}
