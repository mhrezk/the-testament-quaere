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
    public PersonDetails modifyPersonDetails(Long id, PersonDetails personDetails) {
        PersonDetails newPersonDetails = personDetailsRepository.findById(id).orElseThrow();
        Person newPerson = personRepository.findById(id).orElseThrow();
        newPerson.setFirstName(personDetails.getPerson().getFirstName());
        newPerson.setSecondName(personDetails.getPerson().getSecondName());
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
        newPersonDetails.setDeathDay(personDetails.getDeathDay());
        newPersonDetails.setDeathMonth(personDetails.getDeathMonth());
        newPersonDetails.setDeathYear(personDetails.getDeathYear());
        newPersonDetails.setYearAbbreviation(personDetails.getYearAbbreviation());
        //newPersonDetails.setCalendarSystem(personDetails.getCalendarSystem());
        newPersonDetails.setBiography(personDetails.getBiography());
        newPersonDetails.setLineage(personDetails.getLineage());
        newPersonDetails.setImageURL(personDetails.getImageURL());
        return personDetailsRepository.save(newPersonDetails);
    }
}
