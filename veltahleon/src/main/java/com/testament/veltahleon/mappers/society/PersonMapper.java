package com.testament.veltahleon.mappers.society;

import com.testament.veltahleon.dto.society.PersonDTO;
import com.testament.veltahleon.enumerations.ClosedAnswer;
import com.testament.veltahleon.facades.society.PersonFacade;
import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.enumerations.Gender;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PersonMapper {

    @Autowired
    public PersonFacade personFacade;

    public PersonDTO convertToDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .firstName(person.getFirstName().toUpperCase())
                .secondName(person.getSecondName().toUpperCase())
                .gender(String.valueOf(person.getGender()))
                .raceName(person.getRaceName().getName())
//                .birthDay(String.valueOf(person.getBirthYear().getDay().getDayNumber()))
//                .birthDayName(person.getBirthYear().getDay().getName())
//                .birthMonth(String.valueOf(person.getBirthYear().getMonth().getMonthNumber()))
//                .birthMonthName(person.getBirthYear().getMonth().getName())
//                .birthYear(String.valueOf(person.getBirthYear().getEpoch().getYearNumber()))
//                .birthYearAbbreviation(person.getBirthYear().getEpoch().getAbbreviation())
//                .deathDay(String.valueOf(person.getDeathYear().getDay().getDayNumber()))
//                .deathDayName(person.getDeathYear().getDay().getName())
//                .deathMonth(String.valueOf(person.getDeathYear().getMonth().getMonthNumber()))
//                .deathMonthName(person.getDeathYear().getMonth().getName())
//                .deathYear(String.valueOf(person.getDeathYear().getEpoch().getYearNumber()))
//                .deathYearAbbreviation(person.getDeathYear().getEpoch().getAbbreviation())
//                .religion(Optional.of(person.getReligion().getName()).orElseGet(() -> "None"))
//                .rank(person.getRank().getName())
//                .title(person.getTitle().getName())
//                .nation(person.getNation().getName())
//                .imageURL(person.getImageURL())
//                .biography(person.getBiography())
//                .job(person.getJob().getName())
//                .isBastard(String.valueOf(person.getIsBastard()))
                .build();
    }

    public Person convertToEntity(PersonDTO personDTO) {
        Person p = new Person();
//        String[] birthDate = personDTO.getBirthDate().split("/", 3);
//        String[] deathDate = personDTO.getDeathDate().split("/", 3);
        p.setFirstName(personDTO.getFirstName());
        p.setSecondName(personDTO.getSecondName());
        p.setGender(Gender.valueOf(personDTO.getGender()));
        p.setRaceName(personFacade.getRace(personDTO.getRaceName()));
//        p.setReligion(personFacade.getReligion(personDTO.getReligion()));
//        p.setRank(personFacade.getRank(personDTO.getRank()));
//        p.setNation(personFacade.getNation(personDTO.getNation()));
//        p.setBirthYear(personFacade.getYear(Integer.parseInt(birthDate[0]), Integer.parseInt(birthDate[1]), Integer.parseInt(birthDate[2])));
//        p.setDeathYear(personFacade.getYear(Integer.parseInt(deathDate[0]), Integer.parseInt(deathDate[1]), Integer.parseInt(deathDate[2])));
//        p.setTitle(personFacade.getTitle(personDTO.getTitle()));
//        p.setJob(personFacade.getJob(personDTO.getJob()));
//        p.setBiography(personDTO.getBiography());
//        p.setImageURL(personDTO.getImageURL());
//        p.setIsBastard(ClosedAnswer.valueOf(personDTO.getIsBastard()));
        return p;
    }
}
