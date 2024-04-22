package com.testament.veltahleon.mappers.society;

import com.testament.veltahleon.dto.society.PersonDTO;
import com.testament.veltahleon.facades.society.PersonFacade;
import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.model.enumeration.Gender;
import com.testament.veltahleon.services.ifc.history.RaceService;
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
                .name(person.getName())
                .gender(String.valueOf(person.getGender()))
                .raceName(Optional.ofNullable(person.getRace().getName()).orElse("N/A"))
//                .birthDay(Optional.ofNullable(person.getBirthYear().getDay().getDayNumber()).orElse("N/A"))
//                .birthMonth(Optional.ofNullable(person.getBirthYear().getMonth().getMonthNumber()).orElse("N/A"))
//                .birthYear(Optional.ofNullable(person.getBirthYear().getEpoch().getYearNumber()).orElse("N/A"))
//                .deathDay(Optional.ofNullable(person.getDeathYear().getDay().getDayNumber()).orElse("N/A"))
//                .deathMonth(Optional.ofNullable(person.getDeathYear().getMonth().getMonthNumber()).orElse("N/A"))
//                .deathYear(Optional.ofNullable(person.getDeathYear().getEpoch().getYearNumber()).orElse("N/A"))
//                .nation(Optional.ofNullable(person.getNation().getName()).orElse("N/A"))
//                .religion(Optional.ofNullable(person.getReligion().getName()).orElse("N/A"))
//                .family(Optional.ofNullable(person.getFamily().getFamilyName()).orElse("N/A"))
//                .title(Optional.ofNullable(person.getTitle().getName()).orElse("N/A"))
//                .job(Optional.ofNullable(person.getJob().getName()).orElse("N/A"))
//                .biography(Optional.ofNullable(person.getBiography()).orElse("N/A"))
//                .imageURL(Optional.ofNullable(person.getImageURL()).orElse("N/A"))
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
