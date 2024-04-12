package com.testament.veltahleon.mapper.society;

import com.testament.veltahleon.dto.society.PersonDTO;
import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.services.ifc.society.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class PersonMapper {

    @Autowired
    public PersonService service;

    public PersonDTO convertToDTO(Person person) {
        int firstElement = 0;
        int lastElement = 1;
        return PersonDTO.builder()
                .name(person.getName())
                .gender(String.valueOf(person.getGender()))
                .race(person.getRace().getName())
                .religion(person.getReligion().getName())
                .job(person.getJob().getName())
                .nation(person.getNation().getName())
                .title(person.getTitle().getName())
                .familyName(person.getFamily().getFamilyName())
                .biography(person.getBiography())
                .imageURL(person.getImageURL())
                .birthYear(person.getYearBirthAndDeath().get(firstElement).getEpoch().getYearNumber())
                .deathYear(person.getYearBirthAndDeath().get(lastElement).getEpoch().getYearNumber())
                .build();
    }

//    public Person convertToEntity(PersonDTO personDTO) {
//        return new Person();
//    }
}
