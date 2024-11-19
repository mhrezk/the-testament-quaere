package com.testament.veltahleon.mappers.society;

import com.testament.veltahleon.dto.society.PersonDetailsDTO;
import com.testament.veltahleon.enumerations.Gender;
import com.testament.veltahleon.enumerations.Lineage;
import com.testament.veltahleon.enumerations.MaritalStatus;
import com.testament.veltahleon.facades.society.PersonFacade;
import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.model.entities.society.PersonDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersonDetailsMapper {

    @Autowired
    public PersonFacade personFacade;

    public PersonDetailsDTO convertToDTO(PersonDetails personDetails) {
        return PersonDetailsDTO.builder()
                .id(personDetails.getId())
                .firstName(personDetails.getPerson().getFirstName())
                .secondName(personDetails.getPerson().getSecondName())
                .raceName(personDetails.getPerson().getRaceName().getName())
                .gender(String.valueOf(personDetails.getPerson().getGender()))
                .job(personDetails.getJob().getName())
                .biography(personDetails.getBiography())
                .religion(personDetails.getReligion().getName())
                .rank(personDetails.getRank().getName())
                .nation(personDetails.getNation().getName())
                .epithet(personDetails.getEpithet())
                .age(personDetails.getAge())
                .birthDay(personDetails.getBirthDay())
                .birthMonth(personDetails.getBirthMonth())
                .birthYear(personDetails.getBirthYear())
                .birthYearAbbreviation(personDetails.getBirthYearAbbreviation())
                .deathDay(personDetails.getDeathDay())
                .deathMonth(personDetails.getDeathMonth())
                .deathYear(personDetails.getDeathYear())
                .deathYearAbbreviation(personDetails.getDeathYearAbbreviation())
                .imageURL(personDetails.getImageURL())
                .lineage(String.valueOf(personDetails.getLineage()))
                .maritalStatus(String.valueOf(personDetails.getMaritalStatus()))
                .build();
    }

    public PersonDetails convertToEntity(PersonDetailsDTO personDetailsDTO) {
        PersonDetails personDetails = new PersonDetails();
        Person person = new Person();
        personDetails.setId(personDetailsDTO.getId());
        person.setId(personDetails.getId());
        person.setFirstName(personDetailsDTO.getFirstName().toUpperCase());
        person.setSecondName(personDetailsDTO.getSecondName().toUpperCase());
        person.setGender(Gender.valueOf(personDetailsDTO.getGender()));
        person.setRaceName(personFacade.getRace(personDetailsDTO.getRaceName()));
        personDetails.setPerson(person);
        personDetails.setNation(personFacade.getNation(personDetailsDTO.getNation()));
        personDetails.setJob(personFacade.getJob(personDetailsDTO.getJob()));
        personDetails.setRank(personFacade.getRank(personDetailsDTO.getRank()));
        personDetails.setReligion(personFacade.getReligion(personDetailsDTO.getReligion()));
        personDetails.setEpithet(personDetailsDTO.getEpithet());
        personDetails.setBiography(personDetailsDTO.getBiography());
        personDetails.setAge(personDetailsDTO.getAge());
        personDetails.setBirthDay(personDetailsDTO.getBirthDay());
        personDetails.setBirthMonth(personDetailsDTO.getBirthMonth());
        personDetails.setBirthYear(personDetailsDTO.getBirthYear());
        personDetails.setBirthYearAbbreviation(personDetailsDTO.getBirthYearAbbreviation());
        personDetails.setDeathDay(personDetailsDTO.getDeathDay());
        personDetails.setDeathMonth(personDetailsDTO.getDeathMonth());
        personDetails.setDeathYear(personDetailsDTO.getDeathYear());
        personDetails.setDeathYearAbbreviation(personDetailsDTO.getDeathYearAbbreviation());
//        personDetails.setCalendarSystem(personDetailsDTO.getCalendarSystem());
        personDetails.setLineage(Lineage.valueOf(personDetailsDTO.getLineage()));
        personDetails.setMaritalStatus(MaritalStatus.valueOf(personDetailsDTO.getMaritalStatus()));
        personDetails.setImageURL(personDetailsDTO.getImageURL());
        return personDetails;
    }
}
