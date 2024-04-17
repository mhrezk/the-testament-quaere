package com.testament.veltahleon.mapper.society;

import com.testament.veltahleon.dto.society.PersonDTO;
import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.model.enumeration.Gender;
import com.testament.veltahleon.services.ifc.history.RaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersonMapper {

    @Autowired
    public RaceService raceService;

    public PersonDTO convertToDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .gender(String.valueOf(person.getGender()))
                .raceName(person.getRace().getName())
                .build();
    }

    public Person convertToEntity(PersonDTO personDTO) {
        Person p = new Person();
        p.setName(personDTO.getName());
        p.setRace(raceService.getRaceByName(personDTO.getRaceName()));
        p.setGender(Gender.valueOf(personDTO.getGender()));
        return p;
    }
}
