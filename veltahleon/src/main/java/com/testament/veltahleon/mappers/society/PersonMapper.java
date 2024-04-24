package com.testament.veltahleon.mappers.society;

import com.testament.veltahleon.dto.society.PersonDTO;
import com.testament.veltahleon.exceptions.DataNotFoundException;
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
                .name(person.getName().toUpperCase())
                .gender(String.valueOf(person.getGender()))
                .raceName(Optional.ofNullable(person.getRace().getName()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database")))
                .birthDay(String.valueOf(Optional.of(person.getBirthYear().getDay().getDayNumber()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database"))))
                .birthMonth(String.valueOf(Optional.of(person.getBirthYear().getMonth().getMonthNumber()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database"))))
                .birthYear(String.valueOf(Optional.of(person.getBirthYear().getEpoch().getYearNumber()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database"))))
                .deathDay(String.valueOf(Optional.of(person.getDeathYear().getDay().getDayNumber()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database"))))
                .deathMonth(String.valueOf(Optional.of(person.getDeathYear().getMonth().getMonthNumber()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database"))))
                .deathYear(String.valueOf(Optional.of(person.getDeathYear().getEpoch().getYearNumber()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database"))))
                .nation(Optional.ofNullable(person.getNation().getName()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database")))
                .religion(Optional.ofNullable(person.getReligion().getName()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database")))
                .family(Optional.ofNullable(person.getFamily().getFamilyName()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database")))
                .title(Optional.ofNullable(person.getTitle().getName()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database")))
                .job(Optional.ofNullable(person.getJob().getName()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database")))
                .biography(Optional.ofNullable(person.getBiography()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database")))
                .imageURL(Optional.ofNullable(person.getImageURL()).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database")))
                .build();
    }

    public Person convertToEntity(PersonDTO personDTO) {
        Person p = new Person();
        String[] birthDate = personDTO.getBirthDate().split("/", 3);
        String[] deathDate = personDTO.getDeathDate().split("/", 3);
        p.setName(personDTO.getName());
        p.setRace(personFacade.getRace(personDTO.getRaceName()));
        p.setNation(personFacade.getNation(personDTO.getNation()));
        p.setJob(personFacade.getJob(personDTO.getJob()));
        p.setTitle(personFacade.getTitle(personDTO.getTitle()));
        p.setBiography(personDTO.getBiography());
        p.setImageURL(personDTO.getImageURL());
        p.setReligion(personFacade.getReligion(personDTO.getReligion()));
        p.setFamily(personFacade.getFamily(personDTO.getFamily()));
        p.setGender(Gender.valueOf(personDTO.getGender()));
        p.setBirthYear(personFacade.getYear(Integer.parseInt(birthDate[0]), Integer.parseInt(birthDate[1]), Integer.parseInt(birthDate[2])));
        p.setDeathYear(personFacade.getYear(Integer.parseInt(deathDate[0]), Integer.parseInt(deathDate[1]), Integer.parseInt(deathDate[2])));
        return p;
    }
}
