package com.testament.veltahleon.mappers.society;

import com.testament.veltahleon.dto.society.PersonDTO;
import com.testament.veltahleon.facades.society.PersonFacade;
import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.enumerations.Gender;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                .build();
    }

    public Person convertToEntity(PersonDTO personDTO) {
        Person p = new Person();
        p.setFirstName(personDTO.getFirstName());
        p.setSecondName(personDTO.getSecondName());
        p.setGender(Gender.valueOf(personDTO.getGender()));
        p.setRaceName(personFacade.getRace(personDTO.getRaceName()));
        return p;
    }
}
