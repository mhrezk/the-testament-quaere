package com.testament.veltahleon.rest.society;

import com.testament.veltahleon.dto.society.PersonDTO;
import com.testament.veltahleon.mappers.society.PersonMapper;
import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.society.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/society")
@RequiredArgsConstructor
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonMapper personMapper;

//    @Autowired
//    private ModelMapper modelMapper;

    @GetMapping("/persons")
    public ResponseEntity<CustomResponse> getPaginatedPersons(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<PersonDTO> personsDTO;
        List<Person> persons;
        int page = 0;
        if(pageNumber <= 0) {
            persons = (List<Person>) personService.getPersonsWithPagination(page, pageSize);
        } else {
            page = pageNumber;
            persons = (List<Person>) personService.getPersonsWithPagination((page - 1), pageSize);
        }
        personsDTO = persons.stream().map(p -> personMapper.convertToDTO(p)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", personsDTO))
                .message(persons.size() + " persons retrieved!")
                .build()
        );
    }

    @GetMapping("/persons/all")
    public ResponseEntity<CustomResponse> getAllPersons() {
        List<Person> persons = (List<Person>) personService.getPersons();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", persons))
                .message("All persons retrieved!")
                .build()
        );
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<CustomResponse> getPersonByID(@PathVariable Long id) {
        PersonDTO personDTO = personMapper.convertToDTO(personService.getPersonByID(id));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", personDTO))
                .message("Person retrieved!")
                .build()
        );
    }

    @GetMapping("/person/name")
    public ResponseEntity<CustomResponse> getPersonByPersonName(@RequestParam(value = "personName") String personName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", personService.getPersonByName(personName)))
                .message("Person retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/people/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getPersonImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/people/" + fileName));
    }

    @PostMapping("/save/person")
    public ResponseEntity<CustomResponse> savePerson(@RequestBody PersonDTO personDTO) {
        //Person person = modelMapper.map(personDTO, Person.class);
        Person person = personMapper.convertToEntity(personDTO);
        PersonDTO savedPersonDTO = personMapper.convertToDTO(personService.savePerson(person));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", savedPersonDTO))
                .message("Person saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/person/{id}")
    public ResponseEntity<CustomResponse> deletePersonByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", personService.deletePersonByID(id)))
                .message("Person deleted!")
                .build()
        );
    }

    @PatchMapping("/update/person/{id}")
    public ResponseEntity<CustomResponse> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", personService.updatePerson(id, person)))
                .message("Person updated!")
                .build()
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        Person person = personMapper.convertToEntity(personDTO);
        PersonDTO updatedPersonDTO = personMapper.convertToDTO(personService.modifyPerson(id, person));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", updatedPersonDTO))
                .message("Person updated!")
                .build()
        );
    }
}
